import { Component } from '@angular/core';
import { ReaderService } from './reader.service';
import { ActivatedRoute } from "@angular/router";
import { Comment } from '../models/comics/comment';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
    selector: 'reader',
    templateUrl: './reader.component.html',
    styleUrls: ['./reader.component.css']
})
export class ReaderComponent {
    title = 'Genre';

    constructor(private readerService: ReaderService, private route: ActivatedRoute){
      this.readerService = readerService;
      this.route = route;
    }

    chapter;
    seriesId;
    nextChap;
    prevChap;
    notFound = false;
    loading = false;
    comments;
    chapLiked;

    chapterComments: Comment[];
    editComment =  new FormGroup({
      chapterId: new FormControl(this.route.snapshot.paramMap.get("id")),
      username: new FormControl(document.cookie.split("=")[1]),
      comment: new FormControl('')
    });

    likeObj = new FormGroup({
      _id: new FormControl(''),
      seriesId: new FormControl("helllo")
    });

    postComment(comment){
        comment.chapterId = this.chapter._id;
        comment.username = "josuke";
      this.readerService.postComment(comment)
        .subscribe( data => {
          console.log("inside postComment");
          console.log(data);
        });

       (<HTMLInputElement>document.getElementById('comment-box')).value = "";
      this.comments.unshift(comment);
    }

    getComments(){
        this.readerService.getComments(this.chapter._id)
            .subscribe( data => {
                console.log("inside getComments");
                console.log(data);
                this.comments = data;
                this.comments.reverse();
            });
    }

    hasLikedChapter() {
        console.log(this.chapter._id);
        this.readerService.hasUserLikedChapter(this.chapter._id)
            .subscribe( data => {
                console.log("inside getComments");
                console.log(data);
                this.chapLiked = data;

                if(this.chapLiked == true) {
                    let like_text = document.getElementById("like_text");
                    like_text.innerText = 'Liked';
                    document.getElementById('heartTag').style.color = 'red';
                }else {
                    let like_text = document.getElementById("like_text");
                    like_text.innerText = 'Like';
                    document.getElementById('heartTag').style.color = 'gray';
                }
            });
    }

    callLikeChapter(element){
      // let currentId = window.location.href.split("/")[4]
      console.log(this.likeObj.value);
      this.readerService.likeChapter(this.likeObj.value)
        .subscribe( data => {
          console.log("inside callLikeChapter");
          console.log(data);
        });
      let like_text = document.getElementById("like_text");

      if(like_text.innerText == 'Like') {
          like_text.innerText = 'Liked';
          element.style.color = "red";
      }else {
          like_text.innerText = 'Like';
          element.style.color = "#e0e0e0";
      }
    }

    callGetChapter(){
        if(this.loading == false) {
            this.loading = true;
            this.seriesId = this.route.snapshot.paramMap.get("seriesId");
            let chapNum = this.route.snapshot.paramMap.get("chapNum");
            console.log("id", this.seriesId, this.route.snapshot.paramMap);
            this.nextChap = parseInt(chapNum) + 1;
            this.prevChap = parseInt(chapNum) -  1 < 0 ? 0 : parseInt(chapNum) -  1;
            console.log("calling callGetChapter");
            this.readerService.getChapter(this.seriesId,chapNum)
                .subscribe( data=> {
                    console.log("inside callGetChapter");
                    console.log(data, "heey");
                    this.loading = false;
                    if (data == null){
                        this.notFound = true;
                    }else {
                        this.chapter = data;
                        this.getComments();
                        this.hasLikedChapter();
                        this.likeObj.setValue({
                            _id: this.chapter._id,
                            seriesId: this.chapter.seriesId
                        });

                    }
                });
        }

    }

    callGetChapterNext(){

        if(this.loading == false) {
            this.loading = true;
            this.seriesId = this.route.snapshot.paramMap.get("seriesId");
            let chapNum = this.nextChap;

            console.log("id", this.seriesId, this.route.snapshot.paramMap);
            this.nextChap = parseInt(chapNum) + 1;
            this.prevChap = parseInt(chapNum) -  1 < 0 ? 0 : parseInt(chapNum) -  1;
            this.readerService.getChapter(this.seriesId,chapNum)
                .subscribe( data=> {
                    console.log("inside callGetChapter");
                    console.log(data, "heey");
                    this.loading = false;
                    if(data == null) {
                        chapNum = parseInt(chapNum) - 1;
                        this.nextChap = chapNum + 1;
                        this.prevChap = chapNum - 1;
                    }else {
                            this.chapter = data;
                            this.getComments();
                            this.hasLikedChapter();
                            this.likeObj.setValue({
                                _id: this.chapter._id,
                                seriesId: this.chapter.seriesId
                            });

                    }
                });
        }
    }

    callGetChapterPrev(){
        if(this.loading == false) {
            this.loading = true;
            this.seriesId = this.route.snapshot.paramMap.get("seriesId");
            let chapNum = this.prevChap;
            if(chapNum == 0) {
                chapNum = parseInt(chapNum) + 1;
                this.nextChap = chapNum + 1;
                this.prevChap = chapNum - 1;
                this.loading = false;
                return;
            }
            console.log("id", this.seriesId, this.route.snapshot.paramMap);
            this.nextChap = parseInt(chapNum) + 1;
            this.prevChap = parseInt(chapNum) -  1 < 0 ? 0 : parseInt(chapNum) -  1;
            this.readerService.getChapter(this.seriesId,chapNum)
                .subscribe( data=> {
                    console.log("inside callGetChapter");
                    console.log(data, "heey");
                    this.loading = false;
                    if(data == null) {
                        chapNum = parseInt(chapNum) + 1;
                        this.nextChap = chapNum + 1;
                        this.prevChap = chapNum - 1;
                    }else {
                            this.chapter = data;
                            this.getComments();
                            this.hasLikedChapter();
                            this.likeObj.setValue({
                                _id: this.chapter._id,
                                seriesId: this.chapter.seriesId
                            });

                    }
                });
        }
    }

    ngOnInit(){
        console.log("inside ngOnInit");
        this.callGetChapter();
    }

}
