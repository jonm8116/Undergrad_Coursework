sub regexfun(){
    my $stringparam = $_[0];
    print "string param $stringparam \n";
    if($stringparam =~ m/^very\w+\s+ cat$/){
        print "matches string \n";   
    }
    else{
        print "it don't match boi \n";
    }

}

sub secondtest(){
    my $stringparam = $_[0]; 
    print "second string param $stringparam \n";
    if($stringparam =~ m/\b4\b/){
        print " \\b match \n";
    } else{
        print " \\b non match \n";
    }
}

my $stringvar = "very agile fluffy cat";
&regexfun($stringvar);

&secondtest("4 4asdfafdadsd4 ");
