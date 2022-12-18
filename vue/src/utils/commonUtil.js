import {ElLoading, ElMessage} from "element-plus";
import axios from "axios";

const userInfo = {
    type: 0,
    id: '',
    userName: '',
    studentNum: '',
    status: 0,
    freeTime: 0,
    maxScore: 500,
    email: ''
}
let destroyFlag = false
export default {
    messageBox,
    readFile,
    delay,
    userInfo,
    dateTranslate,
    loadingWait,
    print,
    judgeInputIsNumber,
    preFixInteger,
    timeStampToTime,
    unloadHandle,
    beforeunloadHandle,
    exportExcel
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
function timeStampToTime(timestamp) {
    let date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
    let h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
    let m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
    let s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
    return Y + M + D + h + m + s;
}

function unloadHandle() {
    if (!destroyFlag) {
        axios({
            method: 'POST',
            url: '/logout'
        })
        localStorage.clear()
    }
}

function beforeunloadHandle() {
    destroyFlag = true
}

function exportExcel(exportArray, tableName) {
    let xlsx = require("xlsx")
    let sheet = xlsx.utils.json_to_sheet(exportArray)
    let book = xlsx.utils.book_new()
    xlsx.utils.book_append_sheet(book, sheet, "sheet1")
    xlsx.writeFile(book, tableName + '.xlsx')
}