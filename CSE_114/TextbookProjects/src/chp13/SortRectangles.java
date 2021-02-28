package chp13;

public class SortRectangles {

	public static void main(String[] args) {
		ComparableRectangle[] rectangles = {new ComparableRectangle(3.4, 5.4),
				new ComparableRectangle(13.24, 55.4)
		};
		java.util.Arrays.sort(rectangles);
		for(Rectangle rectangle: rectangles){
			System.out.print(rectangle + " ");
		}
	}

}
