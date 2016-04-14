
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
			
			var totalChild = step_childs.length;
			var childWidth = (100 / (totalChild - 1) ) - 3 ;
			
			if(key < totalChild){
				ilEl.style.width = childWidth+"%";				
			}
			
			ilEl.setAttribute("step", step.stepNum);
			
			var span = document.createElement('span');
			span.classList.add('bubble');
			
			var spantooltip = document.createElement('span');
			spantooltip.innerHTML = description;
			spantooltip.textContent = description;
			span.appendChild(spantooltip);
			
			var text = document.createElement('text');
			text.innerHTML = step.step;
			text.textContent = step.step;
			text.classList.add('multistep-margin-top');
			
			ilEl.appendChild(span);
			ilEl.appendChild(text);
			
			if(isComplete){
				var newLine = document.createElement('br');
//				var check = document.createElement('span');
//				check.innerHTML = '(complete)';
//				check.textContent = '(complete)';
				
				ilEl.appendChild(newLine);
				ilEl.appendChild(small);
				ilEl.classList.add('completed');
			}
			
			
			
			ulEl.appendChild(ilEl);	
		}
	}
	e.appendChild(ulEl);
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
			ilEl.classList.remove("active");
		}
		if(current_step < step){
			ilEl.classList.remove("active");
			ilEl.classList.add("completed");
		}
		else if(current_step == step){
			ilEl.classList.add("active");
			$(".active").attr("data-content",step);
		}
	}
}
