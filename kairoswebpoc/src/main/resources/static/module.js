//note: Example class that shows how JavaScript can module works, where the fucntion here returne an o bject
//note: Aliasing using task1,task2
//note: revealing module pattern: function that operates and reveals an object.
//note: IIFE - (Immediately Invoked Function Expression) (function(){<All JS code here>}()}

var createWorker = function ()
{
    var workCounter = 0;

    var task1 = function(){
        workCounter +=1;
        console.log("task 1" + workCounter);

    }

    var task2 = function(){
        workCounter +=1;
        console.log("task 2" + workCounter);

    }

    return {
        job1: task1,
        job2: task2
    }
}

var worker = createWorker();

worker.job1();
worker.job2();
worker.job1();
worker.job1();
