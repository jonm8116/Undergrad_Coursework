sub regexfun(){
    my $string = $_[0];
    if($string =~ m/^(\w+\s(\d+))/){
        print "string match \n ";
    } else{
        print "string no match boi \n";
    }


}

&regexfun("Jan 1987 j");
