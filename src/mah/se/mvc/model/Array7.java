package mah.se.mvc.model;

/**
 * Klassen kommer att innehålla en lista med sju int variabler
 * 
 * @author Jonatan Fridsten
 * testar
 */
public class Array7 {

	private int[] array = new int[7];

	/**
	 * Konstrukterar en ny lista
	 * 
	 * @param array
	 */
	public Array7(int[] array) {
		this.array = array;
	}

	/**
	 * Fyller en lista med 0:or
	 */
	public Array7() {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}

    public Array7(int number) {
        for (int i = 0; i < array.length; i++) {
            array[i] = number;
        }
    }

    /**
	 * S�tt ett v�rde i en specifik position
	 * 
	 * @param pos:
	 *            vilken position man ska l�gga i v�rdet
	 * @param value:
	 *            v�rdet som man vill stoppa in
	 */
	public void setElement(int pos, int value) {
		array[pos] = value;
	}

	/**
	 * Returnerar ett v�rde p� en specifik position
	 * 
	 * @param pos:
	 *            vilken position man vill veta v�rdet i
	 * @return : v�rdet man vill veta
	 */
	public int getElement(int pos) {
		return array[pos];
	}

    public int[] getAll() {
        return array;
    }

    public int getLength() {
        return array.length;
    }
    
    public String toString() {
    	String res = "{";
    	for(int i=0; i<array.length; i++) {
    		if(i >= array.length-1 ) {
    			res+=array[i] + " ";
    		}else {
    			res += array[i] + "," + " ";
    		}
    	}
    	res += "}";
    	return res;
    }
   
}
