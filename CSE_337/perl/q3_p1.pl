sub readfile(){
    my $inputfile = "input21.txt";
    open $filedata, $inputfile or die "File not found";
    $numlines=0;
    $numcharacters=0;
    $numwords=0;
    @arrOfstr;
    $i=0;

    while(my $line = <$filedata>){
        chomp($line);
        $numcharacters = $numcharacters + length($line);
        #print "line: $line\n";
        #print "$line \n";
        @arrOfstr[$i] = $line;
        $i=$i+1;
        @arr = split(" ", $line);
        $sizeofarr = @arr;
        $numwords = $numwords + $sizeofarr;
        

        $numlines= $numlines + 1;
    }
    print "lines: $numlines, words: $numwords, characters: $numcharacters \n";
    #print "@arrOfstr[0] \n";
    #print "@arrOfstr[1] \n";
   
   $sizeofarr = @arrOfstr;
   #print "size of arr $sizeofarr \n";
   for($j=$sizeofarr-1; $j>=0; $j=$j-1){
       #print "arr @arrOfstr[$j]\n";
       @arrline = split("", @arrOfstr[$j]);
       $lenofstr = @arrline;
       #print("arrline: @arrline \n");
       #print "length of str $lenofstr \n";
       for($k=$lenofstr-1; $k>=0; $k=$k-1){
           print $arrline[$k];
       }
       print "\n";

   }
}

readfile();
