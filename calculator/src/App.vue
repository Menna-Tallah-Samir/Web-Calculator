<template>
 <div id="app">
        <h1>Calculator</h1>
        <p class="space"></p>
        <div class="container">
            <div id="writes" class="results">{{ current || '0' }}</div>
            <div class="button">
                <div class="button1">
                    <button @click="percent()" class="buttongrey">%</button>
                    <button @click="clear()" class="buttongrey">CE</button>
                    <button @click="clear()" class="buttongrey">C</button>
                    <button @click="backspace()" class="buttongrey">⌫</button>
                </div>
                <div class="button2">
                    <button @click="OneOver()" class="buttongrey">1⁄x</button>
                    <button @click="square()" class="buttongrey">x<span>&#178;</span></button>
                    <button @click="Root()" class="buttongrey">√x</button>
                    <button @click="divide()" class="buttongrey">÷</button>
                </div>
                <div class="button3">
                    <button @click="append('7')" class="buttonnum">7</button>
                    <button @click="append('8')" class="buttonnum">8</button>
                    <button @click="append('9')" class="buttonnum">9</button>
                    <button @click="multiply()" class="buttongrey">×</button>
                </div>
                <div class="button4">
                    <button @click="append('4')" class="buttonnum">4</button>
                    <button @click="append('5')" class="buttonnum">5</button>
                    <button @click="append('6')" class="buttonnum">6</button>
                    <button @click="sub()" class="buttongrey">-</button>
                </div>
                <div class="button5">
                    <button @click="append('1')" class="buttonnum">1</button>
                    <button @click="append('2')" class="buttonnum">2</button>
                    <button @click="append('3')" class="buttonnum">3</button>
                    <button @click="add()" class="buttongrey">+</button>
                </div>
                <div class="button6">
                    <button @click="sign()" class="buttonnum">+/-</button>
                    <button @click="append('0')" class="buttonnum">0</button>
                    <button @click="dot()" class="buttonnum">.</button>
                    <button @click="equal()" class="buttonblue">=</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default{
data(){
    return {
      current: '0',
      first: '',
      second: '',
      operation: '',
      op: '',
      signFlag: '',
    }
  },
  methods: {
    result(){
      this.second=this.current;
      var url = 'http://localhost:8090';
      var x = this;
      if(this.operation=='+'){
        url = url + '/add?first='+ x.first+'&second='+ x.second;
      }else if(this.operation=='-'){
        url = url + '/sub?first='+ x.first+'&second='+ x.second;
      }else if(this.operation=='×'){
        url = url + '/multi?first='+ x.first+'&second='+ x.second;
      }else if(this.operation=='÷'){
        url = url + '/div?first='+ x.first+'&second='+ x.second;
      }
      if(url!='http://localhost:8090'){
        axios.get(url).then(function(response){
                x.current = response.data;
                x.first=response.data;
        });
      }

        this.operation='';
        this.second='';
    },
    equal(){
      this.result();
      this.op='=';
    },
    Root(){
      var f = this;
      axios.get('http://localhost:8090/root?num='+f.current).then(function(response){
             f.current = response.data;
             f.second=response.data;
      });
      this.op='√';
    },
    square(){
    var f = this;
      axios.get('http://localhost:8090/square?num='+f.current).then(function(response){
              f.current = response.data;
              f.second=response.data;
      });
      this.op='squ';
    },
    OneOver(){
    var f = this;
    axios.get('http://localhost:8090/over?num='+f.current).then(function(response){
                  f.current = response.data;
                  f.second=response.data;
    });
    this.op = '1⁄x';
    },
    divide(){
      if(this.operation!=''){
        if(this.second!=''){
          this.result();
          this.operation='÷';
        }else{
          this.operation='÷';
        }
      }else{
        this.operation='÷';
        if(this.first=='E' || this.second=='E'){
           this.first='0';
           this.second='';
           this.operation='';
        }else{
           this.first = this.current;
        }
      }
    },
    multiply(){
      if(this.operation!=''){
        if(this.second!=''){
          this.result();
          this.operation='×';
        }else{
          this.operation='×';
        }
      }else{
        this.operation='×';
        if(this.first=='E' || this.second=='E'){
          this.first='0';
          this.second='';
          this.operation='';
        }else{
          this.first = this.current;
        }
      }
    },
    add(){
      if(this.operation!=''){
        if(this.second!=''){
           this.result();
           this.operation='+';
        }else{
            this.operation='+';
        }
      }else{
        this.operation='+';
        if(this.first=='E' || this.second=='E'){
            this.first='0';
            this.second='';
            this.operation='';
        }else{
            this.first = this.current;
        }
    }
    },
    sub(){
      if(this.operation!=''){
        if(this.second!=''){
          this.result();
          this.operation='-';
        }else{
          this.operation='-';
        }
      }else{
        this.operation='-';
        if(this.first=='E' || this.second=='E'){
          this.first='0';
          this.second='';
          this.operation='';
        }else{
          this.first = this.current;
        }
      }
    },
    append(number){
      if(number==0 && this.current==0){
        this.current ='0';
      }else if(this.current=='0'){
        if(this.first=='0'){
            this.second=number;
        }
        this.current='';
        this.current=this.current+number;
      }else if(this.operation!='' && this.second==''){
        if(this.first=='E' || this.second=='E'){
            this.first='0';
        }
        this.second=number;
        this.current=number;
        this.op='';
      }else{
        if(this.op!=''){
          this.current=number;
          this.op='';
        }else if(this.first=='E'){
          this.current=number;
          this.op='';
          this.first=this.current;
          this.current = this.current+number;
        }else{
           this.current = this.current+number;
        }
      }
    },
    percent(){
      this.current = this.current/100;
      this.op='%';
    },
    clear(){
      this.current='0';
      this.first='';
      this.second='';
      this.operation='';
      this.op='';
    },
    sign(){
      if(this.current==0){
        var t = this;
        this.current = t.current;
      }else if(this.current.charAt(0)=='-'){
          this.current = this.current.slice(1);
      }else {
        this.current = '-' + this.current;
      }
    },
    dot(){
      if(!this.current.includes('.')){
        this.current = this.current + '.';
      }
    },
    backspace(){
      this.current = this.current.slice(0,-1);
    },
  }
}
</script>
  
<style>
body {
    background-color: white;
}
h1{
    margin: auto;
    background-color: #20B2AA;
    text-align: center;
    font-weight: bold;
    color: white;
    border: 1px solid #f2f2f2;
    -webkit-box-shadow: 1px 1px 4px 0px rgba(176,176,176,1);
    -moz-box-shadow: 1px 1px 4px 0px rgba(176,176,176,1);
    box-shadow: 1px 1px 4px 0px rgba(176,176,176,1);
    border-radius: 3px;
}
.space{
    background-color: white;
}
.results,
p{
    font-size: 24px;
  padding: 24px 5px;
  font-weight: 700;
  text-align: right;
  width: 95%;
  border: none;
  background-color: #C0C0C0;
}
.results{
  overflow: auto;
}
.container{
    margin: auto;
    width:365px;
    border: 1px solid #f2f2f2;
    -webkit-box-shadow: 1px 1px 4px 0px rgba(176,176,176,1);
    -moz-box-shadow: 1px 1px 4px 0px rgba(176,176,176,1);
    box-shadow: 1px 1px 4px 0px rgba(176,176,176,1);
    /*background-color: #fff;*/
    height: 440px;
    background-color:#C0C0C0;  
}
.button1,
.button2,
.button3,
.button4,
.button5,
.button6 {
    margin: 0 2px;
    display: inline-block;
    width: 100%;
}
.buttonblue{
    padding: 20px ;
    font-size: 12px;
    border: none;
    border-radius: 3px;
    background-color: #87CEFA;
    float: left;
    width: 85px;
    margin: 0 2px;
}
.buttonnum{
    padding:20px ;
 font-weight: bold;
 font-size: 12px;
 border: none;
 border-radius: 3px;
 background-color: #DCDCDC;
 float: left;
 width: 85px;
 margin: 0 2px;

}
.buttongrey{
    padding: 20px ;
 font-size: 12px;
 border: none;
 border-radius: 3px;
 background-color: #D3D3D3;
 float: left;
 width: 85px;
 margin: 0 2px;
}
</style>
