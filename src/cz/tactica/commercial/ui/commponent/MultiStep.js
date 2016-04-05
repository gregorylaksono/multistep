
var multiStepId = null;
cz_tactica_commercial_ui_commponent_MultiStep = function() {
	var e = this.getElement();
	var state = this.getState();
	var connector = this;
	
	multiStepId = state.multiStepId;
	var childState = state.childProperties;
	var step_childs = JSON.parse(childState);
	
	var ulEl = document.createElement('ul');
	ulEl.classList.add('progress-indicator');
	ulEl.id = multiStepId;
	
	for(var key in step_childs){
		if (step_childs.hasOwnProperty(key)){
			var ilEl = document.createElement('li');
			var step = step_childs[key];
			var isComplete = step.isComplete;
			var description = step.description;
			
			ilEl.setAttribute("step", step.stepNum);
			
			var span = document.createElement('span');
			span.setAttribute("data-toggle", "tooltip");
			span.setAttribute("data-placement", "bottom");
			span.setAttribute("title", description);
			span.classList.add('bubble');
			
			var text = document.createElement('text');
			text.innerHTML = step.step;
			text.textContent = step.step;
			
			ilEl.appendChild(span);
			ilEl.appendChild(text);
			
			if(isComplete){
				var newLine = document.createElement('br');
//				var small = document.createElement('small');
//				small.innerHTML = '(complete)';
//				small.textContent = '(complete)';
				
				ilEl.appendChild(newLine);
				ilEl.appendChild(small);
				ilEl.classList.add('completed');
			}
			
			
			
			ulEl.appendChild(ilEl);	
		}
	}
	e.appendChild(ulEl);
	$('[data-toggle="tooltip"]').tooltip();
}

 
function go_step(step){
	var ulEl = document.getElementById(multiStepId);
	var children = ulEl.childNodes;
	for(var i=0; i<children.length; i++){
		var ilEl = children[i];
		var current_step = null;
		if(ilEl != null){
			current_step = ilEl.getAttribute("step");
			ilEl.classList.remove("completed");						
		}
		if(current_step <= step){
			ilEl.classList.add("completed");
		}
	}
}
