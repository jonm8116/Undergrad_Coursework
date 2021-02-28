sub readfile(){
	my $inputfile = @ARGV[0];
	open $filedata, $inputfile or die "File not found";
	$i=0;
	#print "here";
	my @arr;
	my $i=0;
	%dict;
	while(my $line = <$filedata>) {
		$strlength = length($line);
		$dict{$line} = $strlength;
		#print "$dict{@arrData[0]} \n";
		
	} 
	while(%dict){
		$max=0; 
		$maxkey="";
		foreach my $key (keys %dict){
			#print(" yo key $key \n");
			#$n = keys%dict;
			if($dict{$key}>$max){
				#print(" yo key $dict{$key} \n");
				$max = $dict{$key};
				$maxkey=$key;
			} 
		
		}
		
		$minkey = findmin(\%dict, $max, $maxkey);
		@arr[$i] = $minkey;
		$i=$i+1;
		
		delete $dict{$minkey};
	}
	$j=0;
	print(@arr);
	#print"yo\n";
	#for($j=$arr-1; $j>=0; $j=$j-1){
	#	print "@arr[$j] \n";
	#}
	#print " usernames @usernames";
}
sub findmin(%dict, $max, $maxkey){
	$min=$max;
	$minkey=$maxkey;
	foreach my $key (keys %dict){
		#print(" yo key $key \n");
		#$n = keys%dict;
		if($dict{$key}<$min){
			#print(" yo key $dict{$key} \n");
			$min = $dict{$key};
			$minkey=$key;
		} 
	
	}
	return $minkey;
}

readfile();
