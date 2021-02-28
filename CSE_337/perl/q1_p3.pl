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
		$dict{@arrData[0]} = @arrData[3];
		#print "$dict{@arrData[0]} \n";
		$i= $i+1;
	} 
	my $dictsize = keys % dict;
	#$bool = checkifNeg(\%dict);
	#print "value of method $bool\n";
	@uidArr;
	$counter=0;
	@usernameArr;
	@posArr;
	@negArr;
	for(my $i=0; $i<$dictsize; $i=$i+1){
		@posArr[$i] = 0;
		@negArr[$i] = 0;
	}
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
		if($max>=0){
			if(@posArr[$max]==0){
				@posArr[$max] = 1;
			} else{
				@posArr[$max] = @posArr[$max] +1;
			}
		}else{
			if(@negArr[abs($max)]==0){
				@negArr[abs($max)] = 1;
			} else{
				@negArr[abs($max)] = @negArr[abs($max)] +1;
			}
		}
		
		delete $dict{$maxkey};
	}
	my $count=0;
	foreach my $item(@posArr){
		if($item!=0){
			print "Group $count has $item users \n"
		}
		$count = $count +1;
	}
	my $count=0;
	foreach my $item(@negArr){
		if($item!=0){
			print "Group -$count has $item users \n"
		}
		$count = $count +1;
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