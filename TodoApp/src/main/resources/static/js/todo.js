    let addTodo = (e) => {
	   e.preventDefault();
	   
	   let todoContents = document.getElementById('todoContent').value;
	   let jsonString = { 'todoContents': todoContents };      
	   let imcomplete = { 'todoContents': todoContents };
	   imcompleteList.push(imcomplete);
	   
	   let tbody = $('#ajaxTodoTable').find('tbody');
	   let tr = $('<tr />');
	   let tdButton1 = $('<td />'); 
	   let tdButton2 = $('<td />');    
	   $('<button />', { 'id': 'doneAjaxBtn', 'type': 'submit'}).appendTo(tdButton1);		        
	   $(tdButton1).appendTo(tr);
	   $('<td />', { 'text': imcomplete.todoContents, 'id': 'todoContents' } ).appendTo(tr);
	   $('<button />', { 'id': 'deleteAjaxBtn', 'type': 'submit' }).appendTo(tdButton2);
	   $(tdButton2).appendTo(tr);       
	   $(tr).appendTo(tbody);    
	   
	   $.ajax({
	        type: 'POST',
		    url: '/todoapp/api/addTodo',
		    data: JSON.stringify(jsonString),
	        contentType: 'application/json',
	        scriptCharset: 'utf-8'
 	   })
 	   .then((result) => {
	      console.log(result);
	      $('input[name=todoContents]').val('');
	       	
       }, () => {
             console.error('Error: ajax connection failed.');
             
          }
       );
              
             
    };
	
    let deleteTodo = (e) => {
	    e.preventDefault();
	             
		let tdList = $(e.target).parent().parent().find('td');
		let id =parseInt($(tdList[0]).text());
		let todoContents = $(tdList[2]).text();
	    let jsonString = { 'id': id, 'todoContents': todoContents	};
		$.ajax({
		     type: 'POST',
			 url: '/todoapp/api/deleteTodo',
			 data: JSON.stringify(jsonString),
			 contentType: 'application/json',
	         scriptCharset: 'utf-8'
		})
		.then((result) => {
	        console.log(result);
		    $(tdList).parent().remove();
		    }, () => {
                console.error('Error: ajax connection failed.');			
		    }
	    );
    };
   
   
   
    let deleteAjaxTodo = (e) => {
	    e.preventDefault();
	            
		let tdList = $(e.target).parent().parent().find('td');
		let todoContents = $(tdList[1]).text();
	    let jsonString = {  'todoContents': todoContents	};
		$.ajax({
		     type: 'POST',
			 url: '/todoapp/api/deleteAjaxTodo',
			 data: JSON.stringify(jsonString),
			 contentType: 'application/json',
	         scriptCharset: 'utf-8'
		})
		.then((result) => {
	        console.log(result);
		    $(tdList).parent().remove();
		    }, () => {
                console.error('Error: ajax connection failed.');			
		    }
	    );
    };
           
           
           
    let doneTodo = (e) => {
	   
	   let tdList = $(e.target).parent().parent().find('td');
	   let id = parseInt($(tdList[0]).text());
	   let todoContents = $(tdList[2]).text();
	   let jsonString = { 'id': id, 'todoContents': todoContents };   
	   let complete = { 'todoContents': todoContents };
	   completeList.push(complete);
	   let tbody = $('#hiddenTable').find('tbody');
       let tr = $('<tr />');	        
	   $('<td />', { 'text': complete.todoContents } ).appendTo(tr);
	   $(tr).appendTo(tbody);

	    $.ajax({
	        type: 'POST',
		    url: '/todoapp/api/doneTodo',
		    data: JSON.stringify(jsonString),
	        contentType: 'application/json',
	        scriptCharset: 'utf-8'
 	    })
 	    .then((result) => {
	        console.log(result);
            $(tdList).parent().remove();
        }, () => {
                console.error('Error: ajax connection failed.');	
            }
        );
	};
   
    let doneAjaxTodo = (e) => {

	    let tdList = $(e.target).parent().parent().find('td');
	    let todoContents = $(tdList[1]).text();
	    let jsonString = {  'todoContents': todoContents };   
	    let complete = { 'todoContents': todoContents };
	    completeList.push(complete);
	    let tbody = $('#hiddenTable').find('tbody');
        let tr = $('<tr />');       
	    $('<td />', { 'text': complete.todoContents } ).appendTo(tr);        
	    $(tr).appendTo(tbody);
            
	    $.ajax({
	        type: 'POST',
		    url: '/todoapp/api/doneAjaxTodo',
		    data: JSON.stringify(jsonString),
	        contentType: 'application/json',
	        scriptCharset: 'utf-8'
 	    })
 	    .then((result) => {
	        console.log(result);
            $(tdList).parent().remove();
        }, () => {
                console.error('Error: ajax connection failed.');	
            }
        );      
    };
           
    let showHistory = (e) => {
	    $.ajax({
		    type: 'POST',
		    url: '/todoapp/api/history',
		    data: JSON.stringify({"id": $('#histId').text(),
		                        "todoContents": $('#histContents').text(),
		                       }),
		    contentType: 'application/json',
		    datatype: 'json',
		    scriptCharset: 'utf-8'
	    })
	    .then((result) => {
        let historyList = JSON.parse(result);
        let tbody = $('#historyTable').find('tbody');
        $(tbody).children().remove();
	  
	    historyList.forEach((history, index) => {
	        let tr = $('<tr />');
	        $('<td />', { 'text': history.todoContents }).appendTo(tr);	
            $(tr).appendTo(tbody);
        });
      
	    $("#history").dialog("open");
	    }, () => {
		        console.error('Error: ajax connection failed.');
	        }
	    );
    };	
   
   
    
    
