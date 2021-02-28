sub readfile(){
    print "input file name ";
    my $filename = <STDIN>;
    chomp($filename);

    if( -e $filename){
        #FIRST CHECK SUBDIR
         open $filedata, $filename;
        my $counter=0;
        while(my $line = <$filedata>){
            $counter = $counter +1;
            
        }
        close $filedata;
        if(-d "backup"){
            print "Checking backup directory... already exists\n";
            if($counter > 10){
                print "$filename has more than 10 lines. What to do next?\n";
                print "Enter 'c' to backup the first 10 lines, 'o' to overwrite without
                creating a backup\n";
                my $useroption = <STDIN>;
                chomp($useroption);
                while(($useroption ne "c") and ($useroption ne "o")){
                   print "$filename has more than 10 lines. What to do next?\n";
                   print "Enter 'c' to backup the first 10 lines, 'o' to overwrite without
                    creating a backup\n";
                    $useroption = <STDIN>;
                    chomp($useroption);
                    print $useroption ne "c";
                   
                }
                if($useroption eq "c"){
                    open $filedata, $filename;
                    open($writefile, ">backup/$filename");
                    my $linecounter=0;
                    while(my $secondline = <$filedata>){
                        if($linecounter>=10){
                            
                        } else{
                            print $writefile $secondline;
                            $linecounter = $linecounter +1;
                            
                        }
                    } 
                    close $filedata;
                    close $writefile;

                    open $filedata, ">$filename";
                    print $filedata "Perl is cool!";
                    close $filedata;
                
                } elsif($useroption eq "o"){
                    #WITHOUT BACKUP
                    open $filedata, ">$filename";
                    print $filedata "Perl is cool!";
                    close $filedata;
                }
            } #DOES NOT HAVE MORE THAN TEN LINES
            else{
                open $filedata, $filename or die "File not found";
                open($writefile, ">backup/$filename");
                my $linecounter=0;
                while(my $secondline = <$filedata>){
                    if($linecounter>=10){
                            
                        } else{
                            print $writefile $secondline;
                            $linecounter = $linecounter +1;
                            
                        }
                    }
                } 
                close $writefile;
                close $filedata;
                open $filedata, ">$filename";
                print $filedata "Perl is cool!";
                close $filedata;

        } else{
            mkdir "backup";
            print "Checking backup directory... backup directory created\n";
             if($counter > 10){
                print "$filename has more than 10 lines. What to do next?\n";
                print "Enter 'c' to backup the first 10 lines, 'o' to overwrite without
                #creating a backup\n";
                my $useroption = <STDIN>;
                chomp($useroption);
                while(($useroption ne "c") and ($useroption ne "o")){
                   print "$filename has more than 10 lines. What to do next?\n";
                   print "Enter 'c' to backup the first 10 lines, 'o' to overwrite without
                    creating a backup\n";
                    $useroption = <STDIN>;
                    chomp($useroption);
                    
                    #print $useroption;
                }
                if($useroption eq "c"){
                    open $filedata, $filename or die "File not found";
                    open($writefile, ">backup/$filename");
                    my $linecounter=0;
                    while(my $secondline = <$filedata>){
                        if($linecounter>=10){
                            
                        } else{
                            print $writefile $secondline;
                            $linecounter = $linecounter +1;
                            
                        }
                    } 
                    close $filedata;
                    close $writefile;

                    open $filedata, ">$filename";
                    print $filedata "Perl is cool!";
                    close $filedata;
                
                } elsif($useroption eq "o"){
                    #WITHOUT BACKUP
                    open $filedata, ">$filename";
                    print $filedata "Perl is cool!";
                    close $filedata;
                }
            } #DOES NOT HAVE MORE THAN TEN LINES
            else{
                open $filedata, $filename or die "File not found";
                open($writefile, ">backup/$filename");
                my $linecounter=0;
                while(my $secondline = <$filedata>){
                    #print "$secondline \n";
                    if($linecounter>=10){
                            
                        } else{
                            print $writefile $secondline;
                            $linecounter = $linecounter +1;
                            
                        }
                    }
                } 
                close $writefile;
                close $filedata;
                open $filedata, ">$filename";
                print $filedata "Perl is cool!";
                close $filedata;

        } 
    } else{
                open $filedata, ">$filename";
                print $filedata "Perl is cool!";
                close $filedata;

    }


}

readfile();
