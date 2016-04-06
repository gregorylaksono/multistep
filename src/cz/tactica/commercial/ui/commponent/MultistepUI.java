package cz.tactica.commercial.ui.commponent;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("multistep")
@StyleSheet("https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css")
public class MultistepUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MultistepUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		
		
		//CssLayout multi = createMultiLayout(3);
		final MultiStep multiStep = new MultiStep();
		multiStep.setWidth(100, Unit.PERCENTAGE);
		multiStep.addStep("Step 1",1,"Step 1 description");
		multiStep.addStep("Step 2",2,"Step 2 description");
		multiStep.addStep("Step 3",3,"Step 3 description");
		
		Button b = new Button("Go step 2");
		b.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				multiStep.go_step(2);
			}
		});
		
		layout.addComponent(multiStep);
		layout.addComponent(b);
	}

	private CssLayout createMultiLayout(int n) {
		CssLayout layout = new CssLayout();
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.addStyleName("mutli-wrap");
		
		CssLayout stripe = new CssLayout();
		stripe.addStyleName("stripe");
		//layout.addComponent(stripe);
		
		float width = 100 / n;
		
		for(int i=0; i<n ; i++){
			int s = i+1;
			Label b = new Label("<span class='bubble'>"+"Step "+s+"</span>");
			b.setContentMode(ContentMode.HTML);
			b.addStyleName("step");
			b.setWidth(width, Unit.PERCENTAGE);
			layout.addComponent(b);
		}
			
		return layout;
	}

}