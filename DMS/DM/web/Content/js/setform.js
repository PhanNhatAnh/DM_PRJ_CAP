/*
This function is used for assigning values to form elements.
*/
function setForm(form, fields)
{
	for (j in fields) {
		if (form[j]) {
			if (!form[j].type && form[j].length) {
				for (i=0; i<form[j].length; i++)
					if (fields[j] == form[j][i].value)
						form[j][i].checked = true;
			}
			else {
				switch (form[j].type) {
					case 'hidden':
					case 'textarea':
					case 'text':
					case 'select-one':
						//form[j].value = fields[j];
						form[j].value = fields[j].toString().replace(/<br \/>/g,"\r\n");
						break;
					case 'checkbox':
						form[j].checked = (fields[j]) ? true : false;
						break;
					case 'radio':
						form[j].checked = (fields[j] == form[j].value);
						break;
					case 'select-multiple':
						//casts values into an array
						fields[j] = (fields[j] == '') ? [] : fields[j].split(',');
						 
						for (i = 0; i < form[j].options.length; i++){
							form[j].options[i].selected = false;
	
							for (z in fields[j])
								if (fields[j][z] == form[j].options[i].value)
									form[j].options[i].selected = true;
						}
						break;
					default:
						break;
				}
			}
		}
		else {
			alert('Form field ' + j + ' does not exist');
		}
	}
}