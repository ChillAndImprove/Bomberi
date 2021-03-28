
public class SynchronisationDerKonstruktionen {
	
	private Object[][] object;
	private Object[][] object1;
	
	public SynchronisationDerKonstruktionen(Object[][] object, Object[][] object1)
	{
		this.object = object;
		this.object1 = object1;
		synchronisiere();
	}
	
	public void synchronisiere()
	{
		for (int i = 0; i < object.length; i++) {
			for (int j = 0; j < object[i].length; j++) {
				if(object instanceof Steine[][])
				{
					if(((Steine) object[i][j]).getExistiert() == true)
					{
						((Heu) object1[i][j]).setExistiert(false);
					}
				}
				else if(object1 instanceof Steine[][])
				{
					if(((Steine) object1[i][j]).getExistiert() == true)
					{
						((Heu) object[i][j]).setExistiert(false);
					}
				}
			}
		}
	}
}
