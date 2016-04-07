package cz.tactica.commercial.ui.commponent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.shared.ui.JavaScriptComponentState;
import com.vaadin.ui.AbstractJavaScriptComponent;

@JavaScript({"jquery-1.7.1.min.js","MultiStep.js"})
@StyleSheet("multistep.css")
public class MultiStep extends AbstractJavaScriptComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -269767096314322675L;
	private List<Step> list = new ArrayList<Step>();
	
	public MultiStep(){
		getState().multiStepId = "multistep_"+new SimpleDateFormat("ddMMyyyyss-SS").format(new Date());
	}
	
	public void go_step(int step){
		com.vaadin.ui.JavaScript.getCurrent().execute("go_step('"+step+"');");
	}

	
	public void addStep(String step,int stepNum, String description){
		list.add(new Step(step,stepNum, description));
		getState().childProperties = new Gson().toJson(list);
	}
	

	@Override
	protected MultiStepState getState() {

		return (MultiStepState) super.getState();
	}
	
	private class Step{
		private String step;
		private String description;
		private int stepNum;
		
		public Step(String step,int stepNum, String description){
			setStep(step);
			setDescription(description);
			setStepNum(stepNum);
		}
		
		public String getStep() {
			return step;
		}
		public void setStep(String step) {
			this.step = step;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

		public int getStepNum() {
			return stepNum;
		}

		public void setStepNum(int stepNum) {
			this.stepNum = stepNum;
		}
		
	}

	
	
}
