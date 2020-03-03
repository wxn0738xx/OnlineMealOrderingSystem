<!DOCTYPE html>
<html>
<head>
  <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
  <script type='text/javascript'>
    var secondsBeforeExpire = 10;
    
    // This will trigger your timer to begin
    var timer = setInterval(function(){
        // If the timer has expired, disable your button and stop the timer
        if(secondsBeforeExpire <= 0){
            clearInterval(timer);
            $("#ExampleButton").prop('disabled',true);
        }
        // Otherwise the timer should tick and display the results
        else{
            // Decrement your time remaining
            secondsBeforeExpire--;
            $("#time-remaining").text(secondsBeforeExpire);      
        }
    },1000);
  </script>
</head>
<body>
  This button will be disabled in <span id='time-remaining'></span> seconds.<hr />
  <button id='ExampleButton'>Example Button</button>
</body>
</html>