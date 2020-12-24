import java.util.Arrays;

/**
 * Represent a bar of data
 */
public class Bar implements Comparable<Bar> {

    private String name;
    private int value;
    private String category;

    // Créé une nouvelle barre.
    public Bar(String name, int value, String category){
        this.name = name;
        this.value = value;
        this.category = category;
    }

    // Renvoie le nom de la barre.
    public String getName(){
        return name;
    }

    // Renvoie la valeur de la barre.
    public int getValue(){
        return value;
    }

    // Renvoie la catégorie de la barre.
    public String getCategory(){
        return category;
    }


    // Compare deux barres selon leur valeur.
    public int compareTo(Bar that) {
        if (this.value > that.value ) {
            return 1;
        }
        else if (this.value < that.value) {
            return -1;
        }
        else
            return 0;
    }

    // Exemple d'utilisation.
    public static void main(String[] args){
        Bar[] tBars = new Bar[13];

        tBars[0] = new Bar("adidas",3791,"Sporting Goods");
        tBars[1] = new Bar("Amazon",4528,"Retail");
        tBars[2] = new Bar("American Express",16122,"Financial Services");
        tBars[3] = new Bar("AOL",4531,"Media");
        tBars[4] = new Bar("Apple",6594,"Technology");
        tBars[5] = new Bar("AT&T",25548,"Telecommunications");
        tBars[6] = new Bar("Bacardi",3187,"Alcohol");
        tBars[7] = new Bar("Barbie",2315,"Toys & Games");
        tBars[8] = new Bar("BMW",12969,"Automotive");
        tBars[9] = new Bar("BP",3066,"Energy");
        tBars[10] = new Bar("Budweiser",10684,"Alcohol");
        tBars[11] = new Bar("Burger King",2701,"Restaurants");
        tBars[12] = new Bar("Chanel",4141,"Luxury");
        System.out.print(tBars[10].compareTo(tBars[6]));
        Arrays.sort(tBars);
    }

}
