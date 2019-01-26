<script>
		$(document).ready(function(){
			
			var passed = $('#report ol').find('li.passed').length;
			$('.success-counter').html('Success = ' + passed);
			
			var failed = $('#report ol').find('li.failed').length;
			$('.failure-counter').html('Failures = ' + failed);
			
			var total = passed+failed;
			$('.steps-counter').html('Total Tests Run = ' + total);
			
			var failedPercent = failed/(failed + passed)*100;
			$('.progress-bar-danger').html("Failed "+Math.round(failedPercent,2)+"%");
			$('.progress-bar-danger').css('width', failedPercent+'%');
			
			var passedPercent = passed/(failed + passed)*100;
			$('.progress-bar-success').html("Passed "+Math.round(passedPercent,2)+"%");
			$('.progress-bar-success').css('width', passedPercent+'%');
			
			if (failed >= 1) {
				$('.summary').addClass('failed');
			} else {
				$('.summary').addClass('passed');
			}
        });
</script>