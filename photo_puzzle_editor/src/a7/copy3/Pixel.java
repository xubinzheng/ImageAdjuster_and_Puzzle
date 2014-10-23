package a7.copy3;

public interface Pixel {
	double getRed();
	double getGreen();
	double getBlue();
	double getBrightness();
	boolean equals(Pixel other);
	char asChar();
}
