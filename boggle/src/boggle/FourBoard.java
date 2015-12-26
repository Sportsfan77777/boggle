package boggle;

public class FourBoard extends Board {
	
	static int length = 4;
	
	public FourBoard() {
		super(length);
	}
	
	public void addCubes() {
		cubes.add(new Cube("AAEEGN"));
		cubes.add(new Cube("ELRTTY"));
		cubes.add(new Cube("AOOTTW"));
		cubes.add(new Cube("ABBJOO"));
		
		cubes.add(new Cube("EHRTVW"));
		cubes.add(new Cube("CIMOTU"));
		cubes.add(new Cube("DISTTY"));
		cubes.add(new Cube("EIOSST"));
		
		cubes.add(new Cube("DELRVY"));
		cubes.add(new Cube("ACHOPS"));
		cubes.add(new Cube("HIMNQU"));
		cubes.add(new Cube("EEINSU"));
		
		cubes.add(new Cube("EEGHNW"));
		cubes.add(new Cube("AFFKPS"));
		cubes.add(new Cube("HLNNRZ"));
		cubes.add(new Cube("DEILRX"));
	}
}
