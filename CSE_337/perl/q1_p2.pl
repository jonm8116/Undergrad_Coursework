use Data::Dumper;
sub readfile() {
	my $inputfile = "passwd.txt";
	open $filedata, $inputfile or die "File not found";
	$i=0;
	#print "here";
	my @usernames;
	my $i=0;
	%dict;
	
	while(my $line = <$filedata>) {
		my @arrData = split(":", $line);
		$dict{@arrData[0]} = @arrData[2];
		#print "$dict{@arrData[0]} \n";
		$i= $i+1;
	} 
	#my $dictsize = keys % dict;
	#$bool = checkifNeg(\%dict);
	#print "value of method $bool\n";
	@uidArr;
	$counter=0;
	@usernameArr;
	while(%dict){
		$max=0; 
		$maxkey="";
		#print("yooo\n");
		foreach my $key (keys %dict){
			#print(" yo key $key \n");
			$n = keys%dict;
			#print "dict size $n\n";
			if(!checkifNeg(\%dict)){
				if($max==0){
					$max = $dict{$key};
					$maxkey = $key;
					
				} else{
					if(abs($dict{$key})<abs($max)){
						#print(" yo key $key \n");
						$max = $dict{$key};
						$maxkey=$key;
					#	print("keys $key \n");
					} 
				}
			} else{
				if($dict{$key}>$max){
					#print(" yo key $dict{$key} \n");
					$max = $dict{$key};
					$maxkey=$key;
				#	print("keys $key \n");
				} 
			}
		}
		@uidArr[$counter] = $dict{$maxkey};
		@usernameArr[$counter] = $maxkey;
		$counter = $counter+1;
		#print("vals $dict{$maxkey}");
		delete $dict{$maxkey};
	}
	print "sorted uids:";
	foreach my $item(@uidArr){
		print " $item";
	}
	print "\n";
	print "usernames: ";
	foreach my $item(@usernameArr){
		print " $item";
	}
}

sub checkifNeg(%dict){
	my %hash = %{$_[0]};
	#print Dumper(\%hash);
	foreach $key(keys%hash){
		if($hash{$key}>0){
			return 1;
		}
	}
	return 0;
}

readfile();