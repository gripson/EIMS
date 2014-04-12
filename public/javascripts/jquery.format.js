/**
 * @params Date.prototype.formatStr
 * 作者 ：黄盼青
 *
 *
 * @returns {string}
 */
Date.prototype.formatStr=function(){
    function convert2fixed(str){
        var _num=Number(str);
        if(!isNaN(str)){
            if(_num<10){
                return '0'+_num;
            }else
            {
            return _num;
            }
        }
        return 0;
    }
    var _year=this.getFullYear(),
        _month=this.getMonth()+1,
        _day=this.getDate(),
        _hours=convert2fixed(this.getHours()),
        _minutes=convert2fixed(this.getMinutes()),
        _seconds=convert2fixed(this.getSeconds());
    return _year+"-"+_month+"-"+_day+" "+_hours+":"+_minutes+":"+_seconds;
}
