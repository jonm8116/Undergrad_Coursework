use Data::Dumper;

sub readfile(){
	my $width =  $_[0];
    my $inputfile = @ARGV[0]; #"sample_input_q2.txt";
	open $filedata, $inputfile or die "File not found";
	$i=0;
	#print "here";
	my @arr;
	my $i=0;
	%dict;
	while(my $line = <$filedata>) {
		#print "$line";
		chomp($line);
		#print "$line \n";
		my @strArr = split(" ", $line); 
		$strlength = length($line);
		#$dict{$line} = $strlength;
		#print "$dict{@arrData[0]} \n";
		foreach my $item(@strArr){
			@arr[$i] = $item;
			$i= $i +1;
		}
	} 
	
	#concatenate strings -> .
	$basestr="";
	$arrcounter=0;
	#print @arr;
	while(@arr){
		$bool=1;
		if(length($basestr)+length(@arr[$arrcounter]) + length(" ") <$width){
		    $basestr = $basestr . @arr[$arrcounter];
            $basestr = $basestr . " "; 
			#$basestr = $basestr . @arr[$arrcounter];
			my $arrsize = @arr;
			splice(@arr, $arrcounter, 1);
			#print "array size $arrsize \n";
			#$arrcounter = $arrcounter + 1;
            if(not @arr){
                print $basestr;
            }
		} else{
			print "$basestr \n";
            $basestr="";
            #$basestr = $basestr . @arr[$arrcounter];
            #$basestr = $basestr . " ";
            #splice(@arr, $arrcounter, 1);
			#$arrcounter = $arrcounter + 1;
		}
	
	}
}

#$width = 15;
my $inputwidth =  @ARGV[1]; #$_[0];

&readfile($inputwidth);
