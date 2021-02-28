sub readfile(){
    print "What string do you want to search for?";
    my $search = <STDIN>;
    chomp($search);
    opendir(my $dir, ".") || die "can't open dir";
    @arr = readdir($dir);
    $size = @arr;
    for(my $i=0; $i<$size; $i=$i+1){
        $filename = @arr[$i];
        if(!(-d $filename)){ 
            if(-e $filename and !($filename eq ".") and !($filename eq "..")){
                #print "$filename \n";
                open $filedata, $filename or die "File not found";
                while(my $line = <$filedata>){
                    if($line =~ $search){
                        $isReadable=0;
                        $isWritable=0;
                        $isExcecutable=0;
                        $isTextfile =0;
                        $basestr="";
                        if(-e $filename){
                            $basestr = $basestr . "e";
                        }
                        if(-r $filename){
                            $basestr = $basestr . "r";
                        }
                        if(-w $filename){
                            $basestr = $basestr . "w";
                        }
                        if(-x $filename){
                            $basestr = $basestr . "e";
                        }

                        if(-T $filename){
                            $basestr = $basestr . "T";
                        }
                        print "Found \"$search\" in file $filename............$basestr\n";
                        last;  
                    }
                }
                close $filedata;
            } 
        }
    }

}

readfile();
