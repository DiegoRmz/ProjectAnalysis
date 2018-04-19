class Comms{
    constructor(uri){
        this.urlComponent = uri;
    }

    communicateBackend(dataT, reqT, dataObj, resultOperFunc){
        $.ajax({
            url:        this.urlComponent,
            dataType:   dataT,
            type:       reqT,
            data:       dataObj,
            success: function(result){
                console.log(result);
                resultOperFunc(result);
            }
        }); 
    }
}