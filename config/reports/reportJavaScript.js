<script>
		$(document).ready(function(){
            $('.observation a').click(function(){
                $(this).parent().next().toggleClass('jq-toggle-opened');
                return false;
            }).parent().next().addClass('jq-toggle');
			
			var passed = $('#report ol').find('li.passed').length;
			$('.success-counter').html('Success = ' + passed);
			
			var failed = $('#report ol').find('li.failed').length;
			if (failed>0){failed--;}
			$('.failure-counter').html('Failed = ' + failed);
			
			var failedPercent = failed/(failed + passed)*100;
			$('.progress-bar-danger').html("Failed "+Math.round(failedPercent,2)+"%");
			$('.progress-bar-danger').css('width', failedPercent+'%');
			
			var passedPercent = passed/(failed + passed)*100;
			$('.progress-bar-success').html("Passed "+Math.round(passedPercent,2)+"%");
			$('.progress-bar-success').css('width', passedPercent+'%');
			
			var steps = $('#report ol').find('li.info').length-1;
			$('.steps-counter').html('Steps = ' + steps);
			
			if (failed >= 1) {
				$('.summary').addClass('failed');
			} else {
				$('.summary').addClass('passed');
			}
        });
</script>