package wIwatW;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Test extends ApplicationFrame
{
   public Test( String applicationTitle , String chartTitle )
   {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Wortformen/Rang","Anzahl der Wortform",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
      
      CategoryAxis axis = lineChart.getCategoryPlot().getDomainAxis();
      axis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
      axis.setMaximumCategoryLabelWidthRatio(2);
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset( )
   {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      
//      FrequencyList fl=WordCount.compute().selectByFrequencyOver(6);
      FrequencyList fl=new FrequencyList();
    	FrequencyListField flf = new FrequencyListField();
    	flf.add("Rasmus");
    	flf.setFrequency(5);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Arpana");
    	flf.setFrequency(11);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Sophie");
    	flf.setFrequency(18);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Annina");
    	flf.setFrequency(9);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Henry");
    	flf.setFrequency(17);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Hannah");
    	flf.setFrequency(18);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Chandra");
    	flf.setFrequency(12);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Greta");
    	flf.setFrequency(16);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Moritz");
    	flf.setFrequency(32);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Selina");
    	flf.setFrequency(15);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Paula");
    	flf.setFrequency(18);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Clemens");
    	flf.setFrequency(6);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Nina");
    	flf.setFrequency(5);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Lasse");
    	flf.setFrequency(22);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Desiree");
    	flf.setFrequency(0);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Anna");
    	flf.setFrequency(4);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Dorothea");
    	flf.setFrequency(6);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Kyra");
    	flf.setFrequency(13);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Marie");
    	flf.setFrequency(10);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Marina");
    	flf.setFrequency(7);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Henrik");
    	flf.setFrequency(14);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Alexander");
    	flf.setFrequency(16);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Monika");
    	flf.setFrequency(10);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Theresa");
    	flf.setFrequency(4);
    	fl.add(flf);
    	
    	flf = new FrequencyListField();
    	flf.add("Miriam");
    	flf.setFrequency(14);
    	fl.add(flf);
    	
    	fl.sortAfterFrequency();
      double count1=0, count2=1;
     for(int k=0; k<fl.size();k++)
//           for(int k=0; k<30;k++)
		{
    	 count1+=fl.getFrequencyOfEntry(k);
			//System.out.println(fl.get(k)+" |"+ fl.getFrequencyOfEntry(k)+"|");
//			System.out.println(fl.get(k)+" |"+ fl.getFrequencyOfEntry(k)+"|"+fl.getFrequencyOfEntry(k)*(k+1));
			dataset.addValue( fl.getFrequencyOfEntry(k), "Textsammlung",fl.get(k).get(0)  );
    	 
//    	 dataset.addValue( fl.getFrequencyOfEntry(k)*(k+1), "Konstante",fl.get(k).get(0)  );
//    	 dataset.addValue( count1/count2, "Schnitt",fl.get(k).get(0)  );
    	 System.out.println(fl.get(k)+" |"+ count1/count2+"|"+fl.getFrequencyOfEntry(k));
    	 count2++;
		}
//      dataset.addValue( 15 , "schools" , "1970" );
//      dataset.addValue( 30 , "schools" , "1980" );
//      dataset.addValue( 60 , "schools" ,  "1990" );
//      dataset.addValue( 120 , "schools" , "2000" );
//      dataset.addValue( 240 , "schools" , "2010" );
//      dataset.addValue( 300 , "schools" , "2014" );
      return dataset;
   }
   public static void main( String[ ] args ) 
   {
	   Test chart = new Test(
      "Zipf'sches Gesetz" ,
      "Zipf'sches Gesetz");

      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
}