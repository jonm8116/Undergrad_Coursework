use Data::Dumper;

sub readfile(){
    my $inputfile = "passwd.txt";
    open $filedata, $inputfile or die "File not found";
    my @groupid;
    my @userid;
    my $counter=0;

    while(my $line = <$filedata>){
        chomp($line);
        
        my @strArr = split(":",  $line);
        @userid[$counter] = @strArr[2];
        @groupid[$counter] = @strArr[3]; 
        $counter = $counter + 1;
    }
    my $groupindex = @groupid;
    #print "group size $groupindex \n";
    $groupindex = int(rand() * $groupindex);
    #print "group rand number is $groupindex \n";
    #print "user ids\n";
    #print Dumper(\@userid);
    close($filedata);
          
    #print "group ids \n";
    #print Dumper(\@groupid);
    $max = findmax(\@userid);
    #print "this is the cur max $max \n";
    $newUserid = $max+1;
    $newGroupid = @groupid[$groupindex];
   
    #sample insertion: Zhibo Yang:*:100:91:New User:/home/:bin/bash
    $printstr = "\nJonathan Mathai:*:" . $newUserid .":" . $newGroupid . ":" . "New User:/home/:bin/bash";
    
    #print "$printstr \n";
    #print $filedata "$printstr \n";
    open($filedata, ">>passwd.txt");
    print $filedata $printstr;
    close($filedata);
}

sub findmax(){
    @userArr = @{$_[0]};
    $max=0;
    $sizeofarr = @userArr;
    #print "@userArr \n";
    #print "size of arr $sizeofarr \n";

    for($i=0; $i<$sizeofarr; $i=$i+1){
        if(@userArr[$i] > $max){
           $max= @userArr[$i];
        }
        #print "arr val @userArr[$i] \n";
    }
    return $max;
    	
}

readfile();
