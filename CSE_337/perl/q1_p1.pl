sub readfile(){
	my $inputfile = "passwd.txt";
	open $filedata, $inputfile or die "File not found";
	$i=0;
	#print "here";
	my @usernames;
	my $i=0;
	while(my $line = <$filedata>) {
		my @arrData = split(":", $line);
		my $userid = @arrData[2];
		if($userid % 2 == 0){
			#@usernames[$i] = @arrData[0];
			#$i= $i + 1;
			print "User name is @arrData[0] and userid is @arrData[2] \n";
		}
		#for($i=0; $i<)
	} 
	#print " usernames @usernames";
}

readfile()