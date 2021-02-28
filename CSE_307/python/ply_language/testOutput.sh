for i in {1..40}
do
  python stonyBrookScript.py input_$i.txt > diff.txt
  echo "difference in file $i"
  diff diff.txt -w output_$i.txt
  echo ""
done
