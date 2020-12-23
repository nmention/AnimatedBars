import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DiagBar {

    private final String title;               // bar chart title
    private final String xAxisLabel;          // x-axis label
    private final String dataSource;          // data source
    private String caption;                   // caption
    private ArrayList<String> names;          // list of bar names
    private ArrayList<Integer> values;        // list of bar values
    private ArrayList<Color> colors;          // list of bar colors

    private ArrayList<String> brand = new ArrayList<>();
    private ArrayList<Integer> stockValue = new ArrayList<>();
    private ArrayList<String> category = new ArrayList<>();
    private HashMap<String, Color> colorTable = new HashMap<>();

    int maximumStockValue = 0;

    /**
     * Créé un nouveau diagramme à barre.
     *
     * @param title      le titre
     * @param xAxisLabel la légende de l'axe horizontal
     * @param dataSource l'origine des données
     */
    public DiagBar(String title, String xAxisLabel, String dataSource) {
        if (title == null) throw new IllegalArgumentException("name is null");
        if (xAxisLabel == null) throw new IllegalArgumentException("x-axis label is null");
        if (dataSource == null) throw new IllegalArgumentException("data source is null");
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.dataSource = dataSource;
        reset();
    }

    /**
     * Permet de réinitialiser un DiagBar
     */
    public void reset() {
        brand.clear();
        stockValue.clear();
        category.clear();
    }

    /**
     * Sets the caption of this bar chart.
     * The caption is drawn in the lower-right part of the window.
     *
     * @param caption the caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Ajoute une barre au diagramme.
     * La longueur de la barre est proportionnelle à sa valeur.
     * Les barre sont tracées de haut en bas dans l'ordre où elle sont ajoutées.
     * Toutes les barres d'une même catégorie sont de la même couleur.
     *
     * @param name     le nom de la barre
     * @param value    la valeur de la barre
     * @param category la catégorie de la barre
     */
    public void add(String name, int value, String category) {
        this.brand.add(name);
        this.stockValue.add(value);
        this.category.add(category);
        if (value > maximumStockValue) {
            maximumStockValue = value;
        }

        if (!colorTable.containsKey(category)) {
            Color selected = BarColors.colors[colorTable.size() % BarColors.colors.length];
            colorTable.put(category, selected);
        }

    }

    public void draw() {
        double xscale = maximumStockValue + (maximumStockValue * 0.2);
        double yscale = brand.size()*5 + brand.size()*0.05*5;
        StdDraw.setXscale(0, xscale);
        StdDraw.setYscale(0, yscale);
        StdDraw.text(xscale/2,yscale - yscale * 0.01,title);
        StdDraw.textRight(xscale,yscale * 0.05,dataSource);
        StdDraw.textLeft(0, yscale- yscale * 0.03, xAxisLabel);
        for (int i = 0; i < brand.size(); i++) {
            StdDraw.setPenColor(colorTable.get(category.get(i)));
            StdDraw.filledRectangle((double) stockValue.get(i) / 2, (double) 5*(i+0.5), (double) stockValue.get(i) / 2, (double) 2);
            StdDraw.setPenColor();
            StdDraw.textRight((double) stockValue.get(i) - xscale * 0.01,(double) 5*(i + 0.5) , brand.get(i));
            StdDraw.textRight(xscale,yscale * 0.1,caption);
            StdDraw.textLeft((double) stockValue.get(i) + (double) stockValue.get(i) * 0.01,(double) 5*(i + 0.5), Integer.toString(stockValue.get(i)));
        }
    }

    // Exemple pour mise au point
    public static void main(String[] args) {
        // création du diagramme
        String title = "Famous brands";
        String xAxis = "Stock value $(million)";
        String source = "Source: Interbrand website";
        DiagBar diag = new DiagBar(title, xAxis, source);
        diag.setCaption("2000-01-01");

        // ajout des barres suivantes au diagramme
        diag.add("adidas", 3791, "Sporting Goods");
        diag.add("Amazon", 4528, "Retail");
        diag.add("American Express", 16122, "Financial Services");
        diag.add("AOL", 4531, "Media");
        diag.add("Apple", 6594, "Technology");
        diag.add("AT&T", 25548, "Telecommunications");
        diag.add("Bacardi", 3187, "Alcohol");
        diag.add("Barbie", 2315, "Toys & Games");
        diag.add("BMW", 12969, "Automotive");
        diag.add("BP", 3066, "Energy");
        diag.add("Budweiser", 10684, "Alcohol");
        diag.add("Burger King", 2701, "Restaurants");
        diag.add("Chanel", 4141, "Luxury");

        // rendu du diagramme
        StdDraw.setCanvasSize(1280, 800);
        StdDraw.enableDoubleBuffering();
        diag.draw();
        StdDraw.show();
    }

}