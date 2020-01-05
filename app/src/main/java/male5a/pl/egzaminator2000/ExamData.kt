package male5a.pl.egzaminator2000

import com.orm.SugarRecord

class ExamData: SugarRecord() {
    var name:String=""
    var date:String=""
    var time:String=""

    public fun GetExamInfo():String{
        return name+" "+ date + " " + time

    }

}