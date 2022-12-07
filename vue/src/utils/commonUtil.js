import {ElMessage} from "element-plus";
import {ElLoading} from 'element-plus';

const userInfo = {
    type:0,
    id:'2222',
    userName:'',
    studentNum:'',
    status:1,
    freeTime:0,
    maxScore:500,
}
export default{
    messageBox,
    readFile,
    delay,
    userInfo,
    dateTranslate,
    loadingWait,
    print,
    judgeInputIsNumber,
    preFixInteger
}
function messageBox(msg, type) {
    ElMessage({
        showClose: false,
        message: msg,
        type: type,
        grouping: true
    })
}
function loadingWait(text,targetId)
{
    return ElLoading.service({
        lock:true,
        text:text,
        background:'rgba(255, 255, 255, 0.85)',
        target:document.getElementById(targetId)
    })
}
export function readFile(file){
    return new Promise(resolve => {
        let reader = new FileReader()
        reader.readAsBinaryString(file)
        reader.onload =ev=>{
            resolve(ev.target.result)
        };
    });
}
function delay(interval =0){
    return new  Promise(resolve => {
        let timer = setTimeout(()=>{
            clearTimeout(timer)
            resolve()
        },interval);
    })
}
function dateTranslate(date)
{
    return date+'-'+'9-1'
}
function print(text)
{
    console.log(text)
}
function judgeInputIsNumber(text)
{
    //单独使用!isNaN(parseFloat(value))会将123abc当成数值，
    // 所以用isFinite额外判断一次
    return !isNaN(parseFloat(text)) && isFinite(text); // false
}
function preFixInteger(num,length)
{
    return (Array(length).join('0')+num).slice(-length)
}