use Data::Dumper;

sub currency(){
    my %currency = (
      eur => 0.81,
      cad => 1.29,
      inr => 65.2,
      jpy => 105.75,
      vnd => 22750,
      krw => 1079.43,
      btc => 0.000088  
    );
    
    print"Exchangeable currency: usd, eur, cad, inr, jpy, vnd, krw, btc\n";
    print "Enter the current currency: ";
    my $currentcur = <STDIN>;
    print "\nEnter the target currency: ";
    my $targetcur = <STDIN>;
    print "\nEnter the amount of money: ";
    my $amount = <STDIN>;
    chomp($currentcur);
    chomp($targetcur);
    chomp($amount);
    
    my $boolcur=0;
    my $booltar=0;
    my $tarcurrenval;
    my $curcurrenval;
    #Dumper(\%currency);
    foreach my $key(keys%currency) {
        print "key: $key \n";
        if($currentcur eq $key){
            #print "$currentcur == $key\n";
            $boolcur=1;
            $curcurrenval = $currency{$currentcur};
        }
        if($targetcur eq $key){
            # print "$targetcur == $key\n";
            $booltar=1;
            $tarcurrenval= $currency{$targetcur};
        }

    }
    print "boolcur $boolcur\n";
    print "booltar $booltar \n";
    if($boolcur==0){
        print "We don't accept $currentcur here \n";
        print "Re-enter values";
        currency();
    }
    if($booltar ==0){
        print "We don't accept $targetcur here \n";
        print "Re-enter values";
        currency();
    }
    if($boolcur !=0 and $booltar!=0){
        $midstep = $amount / $curcurrenval;
        $result = $midstep * $tarcurrenval;

        print "$amount $currentcur is $result $targetcur \n";
    }

    return 0;
}

currency();
