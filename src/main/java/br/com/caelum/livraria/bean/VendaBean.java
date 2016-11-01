package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.caelum.livraria.dao.VendaDAO;
import br.com.caelum.livraria.modelo.Venda;


@Named
@ViewScoped
public class VendaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LineChartModel animatedModel1;
	private BarChartModel animatedModel2;
	
	private Venda venda;
	private List<Venda> vendas;
	
	@Inject
	private VendaDAO dao;
	
	@PostConstruct
	public void init() {
		createAnimatedModels();
	}
	
	public LineChartModel getAnimatedModel1() {
		return animatedModel1;
	}
	
	public BarChartModel getAnimatedModel2() {
		return animatedModel2;
	}
	
	public List<Venda> getVendas(){	
		System.out.println("Vou buscar a Lista de Vendas ------ VendaBean ");
		if(this.vendas == null){
			this.vendas = this.dao.listaTodos();
		}
		return vendas;				
	}
	 
    /**
	 * @return the venda
	 */
	public Venda getVenda() {
		return venda;
	}

	private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Line Chart");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(100);
         
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Bar Charts");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel(); 
        ChartSeries vendaSeries = new ChartSeries();
        vendaSeries.setLabel("Vendas 2016");
        
        List<Venda> vendas = getVendas();
        for(Venda venda:vendas){
        	vendaSeries.set(venda.getLivro().getTitulo(), venda.getQuantidade());
        }
 
     /*   ChartSeries vSeries = new ChartSeries();
        vSeries.setLabel("Vendas 2015");
        
        vendas = getVendas();
        for(Venda venda:vendas){
        	vSeries.set(venda.getLivro().getTitulo(), venda.getQuantidade());
        }*/
        
        model.addSeries(vendaSeries);
/*        model.addSeries(vSeries);
*/         
        return model;
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Preços 2016");
 
        List<Venda> vendas2 = getVendas();
        for(Venda venda:vendas2){
        	series1.set(venda.getLivro().getId(), venda.getLivro().getPreco());
        }
        
/*        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);*/
 
/*        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Preços 2016");
 
        vendas2 = getVendas(30);;
        for(Venda venda:vendas2){
        	series2.set(venda.getLivro().getPreco(), (venda.getLivro().getId()+1));
        }*/
        
   /*     series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);*/
 
        model.addSeries(series1);
       /* model.addSeries(series2);*/
         
        return model;
    }
	
	
	
	
}
