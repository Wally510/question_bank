<template>
	<view>
		<view style="z-index: 50; background-color: #ffffff; position: fixed; width: 100%;">
			<view style="display: flex; width: 100%; height: 8%; border-bottom: 2px solid #e5e5e5;">
				<view style="width: 22%; text-align: center; line-height: 50px">
					<text class="biaoqian-zi">{{id}}</text>
				</view>
				<view style="width: 27%; text-align: center; line-height: 50px;" @click="tixingpx">
					<text class="biaoqian-zi">{{tx}}</text>
					<text class="uni-panel-icon uni-icon">&#xe581;</text>
				</view>
				<view style="width: 27%; text-align: center; line-height: 50px;" @click="zhishidianpx">
					<text class="biaoqian-zi">{{zsd}}</text>
					<text class="uni-panel-icon uni-icon">&#xe581;</text>
				</view>
				<view style="width: 24%; text-align: center; line-height: 50px;" @click="nandupx">
					<text class="biaoqian-zi">{{nd}}</text>
					<text class="uni-panel-icon uni-icon">&#xe581;</text>
				</view>
			</view>
			<view style="border-bottom: 1px solid #e1e1e1; width: 100%; line-height: 50px; min-height: 50px;" :style="{'display':xiala}" v-for="(item, index) in ndtx" @click="changend(item.name)">
				<text>{{item.name}}</text>
			</view>
			<view style="border-bottom: 1px solid #e1e1e1; width: 100%; line-height: 50px; min-height: 50px; padding-bottom: 10px;" :style="{'display':zsdxiala}">
				<checkbox-group style="flex-wrap: wrap; display: flex;">
					<checkbox style="margin-left: 1%; min-width: 20%;" v-for="item in ndtx" :key="item.id" :value="item.value">
						{{item.name}}
					</checkbox>
				</checkbox-group>
				<view style="display: flex;">
					<button style="height: 40px; line-height: 40px; background-color: #d66b00; color: #ffffff;" @click="zsdquxiao">取消</button>
					<button style="height: 40px; line-height: 40px; background-color: #00aaff; color: #ffffff;" @click="zsdquedin">确定</button>
				</view>
			</view>
		</view>
		<scroll-view style="position: absolute; top: 8%; width: 100%; height: 92%;" scroll-y="true" refresher-enabled="true" :refresher-triggered="refresh" @refresherrefresh="onRefresh">
			<uni-swipe-action>
				<!-- <rich-text :nodes="richText"></rich-text> -->
			    <view style="background-color: #fcfcfc; border-top: 1px solid #dcdcdc; border-bottom: 1px solid #dcdcdc; margin-top: 15px;" v-for="(ti,index) in timu1" :key="index" >
			    	<uni-swipe-action-item :right-options="options" @click="onClick(ti.id)" @change="change">
						<view style="margin-top: 10px; margin-bottom: 10px;">
							<view class="gaodu" style="overflow: hidden; text-overflow: ellipsis; min-height: 50px; max-height: 95px; margin-left: 5%; width: 90%;" @click="zsdquedin(index)">
								<rich-text :nodes="'('+(index+1)+') '+ti.problem"></rich-text>
							</view>
							<!-- <view style="margin-left: 5%; color: #d66b00;" :style="{'display':ti.xianshi}" @click="getheight(ti.id)">······</view> -->
							<view style="display: flex; margin-top: 5px; margin-left: 5%; width: 90%;">
								<view class="biaoqian">{{ti.type}}</view>
								<view class="biaoqian">{{ti.points}}</view>
								<view class="biaoqian">{{ti.difficulty}}</view>
							</view>
						</view>
			    	</uni-swipe-action-item>
			    </view>
			</uni-swipe-action>
			<view style="height: 15px;"></view>
		</scroll-view>
		<view style="z-index: 20; text-align: center; position: fixed; background-color: #f1f1f1; width: 60%; height: 20%; top: 35%; left: 20%; border-radius: 10px;" :style="{'display':shanchukuang}">
			<view style="font-size: 20px; width: 100%; position: absolute; top: 20%;">是否删除？</view>
			<view style="display: flex;position: absolute; bottom: 20%; width: 100%;">
				<button style="width: 30%; height: 100%; font-size: 14px; color: #00aaff; border: 1px solid #00aaff;" @click="quxiao">取消</button>
				<button style="width: 30%; height: 100%; font-size: 14px; background-color: #dd524d; border: none; color: #ffffff;" @click="queren">确认</button>
			</view>
		</view>
		<view style="z-index: 10; position: fixed; width: 100%; height: 100%; opacity:0.4; background-color: #000000;" :style="{'display':xiala}"></view>
		<view style="z-index: 10; position: fixed; width: 100%; height: 100%; opacity:0.4; background-color: #000000;" :style="{'display':zsdxiala}"></view>
	</view>
</template>

<script>
	//生成pdf
	import html2canvas from 'html2canvas';
	import jspdf from 'jspdf';
	
	//调用katex
	import 'katex/dist/katex.min.css';
	import katex from 'katex';
	export default {
		data() {
			return {
				options:[
					{
						text: '删除',
						style: {
						    backgroundColor: '#dd524d'
						}
					}
				],
				chapter_id:0,
				delete_id:0,
				refresh:false,
				richText:'设$f(x)=u(x)+v(x),g(x)=u(x)-v(x),$并设$\lim{x->x_{0}}u(x)$与$\lim{x->x_{0}}v(x)$都不存在，下列结论正确的是（ ）',
				timuIndex:0,
				shanchukuang:'none',
				xiala:'none',
				zsdxiala:'none',
				id:'题号',
				tx:'全部题型',
				zsd:'知识点',
				nd:'难度',
				dx:0,
				ndtx:[],
				tixing:[
					{
						id:'1',
						name:'全部题型',
					},
					{
						id:'2',
						name:'选择题',
					},
					{
						id:'3',
						name:'计算题',
					},
					{
						id:'4',
						name:'其他',
					},
				],
				zhishidian:[
					{
						id:'1',
						name:'知识点1',
					},
					{
						id:'2',
						name:'知识点2',
					},
					{
						id:'3',
						name:'知识点3',
					},
					{
						id:'4',
						name:'知识点4',
					},
					{
						id:'5',
						name:'知识点5',
					},
				],
				nandu:[
					{
						id:'1',
						name:'难度',
					},
					{
						id:'2',
						name:'从高到低',
					},
					{
						id:'3',
						name:'从低到高',
					},
				],
				timu1:[],
				timu2:[],
			}
		},
		onLoad(option) {
			console.log(option.id); 
			this.chapter_id = option.id;
			// 获取数据库数据
			uni.request({
			    url: 'http://localhost:8080/main.java.com.atheism.obtainment', // 请求的URL  
			    method: 'POST', // 请求方法POST  
			    data: {  
			        id: option.id, // 请求的数据  
			    },  
			    header: {  
			        'content-type': 'application/json' // 设置请求头  
			    },  
			    success: (res) => {  
			        // 请求成功处理逻辑  
			        console.log(res.data); // 输出服务器返回的数据  
					
					this.timu2 = res.data;
					for(let i=0;i<this.timu2.length;i++){
						const renderedText = this.renderMathInHtml(this.timu2[i].problem);
						this.timu2[i].problem=renderedText;
					}
					this.timu1=this.timu2;
			    },  
			    fail: (err) => {  
			        // 请求失败处理逻辑  
			        console.error('请求失败：', err);  
			    }  
			});
			/* const mathText = '设$f(x)=u(x)+v(x),g(x)=u(x)-v(x),$并设$\\lim\\limits_{x \\rightarrow x_{0}}u(x)$与$\\lim\\limits_{x \\rightarrow x_{0}}v(x)$都不存在，下列结论正确的是（ ）.$\\newline A.$若$\\lim\\limits_{x \\rightarrow x_{0}}f(x)$不存在，则$\\lim\\limits_{x \\rightarrow x_{0}}g(x)$必存在$\\newline B.$若$\\lim\\limits_{x \\rightarrow x_{0}}f(x)$不存在，则$\\lim\\limits_{x \\rightarrow x_{0}}g(x)$必不存在$\\newline C.$若$\\lim\\limits_{x \\rightarrow x_{0}}f(x)$存在，则$\\lim\\limits_{x \\rightarrow x_{0}}g(x)$必不存在$\\newline D.$若$\\lim\\limits_{x \\rightarrow x_{0}}f(x)$存在，则$\\lim\\limits_{x \\rightarrow x_{0}}g(x)$必存在';
			const renderedText = this.renderMathInHtml(mathText);
			this.richText = renderedText; */
		},
		mounted(){
			
			const item = document.querySelectorAll('.gaodu');
			for(let i=0;i<this.timu2.length;i++){
				const renderedText = this.renderMathInHtml(this.timu2[i].problem);
				this.timu2[i].problem=renderedText;
			}
			this.timu1=this.timu2;
			/* for(let i=0;i<this.timu2.length;i++){
				console.log(item[i].offsetHeight);
				if(item[i].offsetHeight>=95){
					this.timu2[i].xianshi='block';
				}
			} */
		},
		methods: {
			getheight(i){
				const item = document.querySelectorAll('.gaodu');
				if (item) {
				  console.log('class为gaodu的元素的高度：', item[i-1].offsetHeight);
				}
			},
			renderMathInHtml(text) {
			    // 使用 KaTeX 渲染数学表达式
			    const renderedText = text.replace(/\$(.*?)\$/g, (match, math) => katex.renderToString(math));
			    return renderedText;
			},
			onRefresh() {
				this.refresh= true;
				// 这里不能直接让refresh直接为false，否则可能会发生下拉加载无法复位的情况
				setTimeout(() => {
					this.refresh = false;
					uni.showToast({
						title:"刷新成功",
						duration:1500,
						icon:"none"
					});
				}, 500);
			},
			change(){
			},
			onClick(id){
				console.log(id);
				this.delete_id = id;
				this.shanchukuang='block';
				this.xiala='block';
			    //点击选项按钮时触发事件	
			    //e = {content,index} ，content（点击内容）、index（下标）、position (位置信息)
			},
			swipeChange(e){
			 	//组件打开或关闭时触发	
			    // left:左侧 ，right：右侧，none：关闭
			},
			quxiao(){
				this.shanchukuang='none';
				this.xiala='none';
			},
			queren(){
				// 获取数据库数据
				uni.request({
				    url: 'http://localhost:8080/main.java.com.atheism.delete', // 请求的URL  
				    method: 'POST', // 请求方法POST  
				    data: {  
				        id: this.delete_id, // 请求的数据  
				    },  
				    header: {  
				        'content-type': 'application/json' // 设置请求头  
				    },  
				    success: (res) => {  
				        // 请求成功处理逻辑  
				        console.log(res.data); // 输出服务器返回的数据  
						
				    },  
				    fail: (err) => {  
				        // 请求失败处理逻辑  
				        console.error('请求失败：', err);  
				    }  
				});
				
				uni.reLaunch({
				    url:"/pages/discipline/question?id="+this.chapter_id,
				})
			},
			zsdquedin(i){
				console.log("查看题目");
				uni.navigateTo({
					url:'/pages/discipline/view?id='+i+'&chapter_id='+this.chapter_id
				});
			},
			tmzhanshi(id){
				if(this.timuIndex != id){
					this.timuIndex = id;
				}else{
					this.timuIndex = 0;
				}
				console.log(this.timuIndex);
			},
			tixingpx(){
				console.log('题型');
				this.ndtx=this.type;
				this.dx=1;
				if(this.xiala=='block'){
					this.xiala='none';
				}else{
					this.xiala='block';
				}
			},
			zhishidianpx(){
				console.log('知识点');
				this.ndtx=this.points;
				this.dx=2;
				if(this.zsdxiala=='block'){
					this.zsdxiala='none';
				}else{
					this.zsdxiala='block';
				}
			},
			zsdquxiao(){
				console.log('取消');
				this.zsdxiala='none';
			},
			nandupx(){
				console.log('难度');
				this.ndtx=this.difficulty;
				this.dx=3;
				if(this.xiala=='block'){
					this.xiala='none';
				}else{
					this.xiala='block';
				}
			},
			changend(name){
				console.log(name);
				if(this.dx==1){
					this.tx=name;
					if(this.tx!='全部题型'){
						this.timu1=[];
						for(let i=0;i<this.timu2.length;i++){
							if(this.timu2[i].type==this.tx){
								this.timu1.push(this.timu2[i]);
							}
						}
					}else{
						this.timu1=this.timu2;
					}
				}
				if(this.dx==3){
					this.nd=name;
					if(this.nd=='难度'){
						var ti=this.timu2;
						for (let g = 0; g < ti.length-1; g++) {
							for (let h = 0; h < ti.length-1; h++) {
								if (parseInt(ti[h].id) > parseInt(ti[h+1].id)) {
									[ti[h],ti[h+1]] = [ti[h+1],ti[h]];
								}
							}
						}
						this.timu1=ti;
					}else if(this.nd=='从高到低'){
						var ti=this.timu2;
						for (let g = 0; g < ti.length-1; g++) {
							for (let h = 0; h < ti.length-1; h++) {
								if (ti[h].difficulty > ti[h+1].difficulty) {
									[ti[h],ti[h+1]] = [ti[h+1],ti[h]];
								}
							}
						}
						this.timu1=ti;
					}else if(this.nd=='从低到高'){
						var ti=this.timu2;
						for (let g = 0; g < ti.length-1; g++) {
							for (let h = 0; h < ti.length-1; h++) {
								if (ti[h].difficulty < ti[h+1].difficulty) {
									[ti[h],ti[h+1]] = [ti[h+1],ti[h]];
								}
							}
						}
						this.timu1=ti;
					}
				}
				this.xiala='none';
			},
		}
	}
</script>

<style>
	page{
		background-color: #f8f8f8;
	}
	.biaoqian{
		border: 1px solid #9c9c9c;
		color: #9c9c9c;
		font-size: 13px;
		padding: 2px;
		border-radius: 6px;
		margin-right: 3%;
	}
	.kuangcolor{
		background-color: #f7f0e3;
	}
	.timukuang1{
		display: block;
	}
	.timukuang2{
		display: none;
	}
	
	@font-face {
		font-family: uniicons;
		font-weight: normal;
		font-style: normal;
		src: url('~@/static/uni.ttf') format('truetype');
	}
	.uni-panel-icon {
	    margin-left: 5px;
	    color: #999999;
	    font-size: 14px;
	    font-weight: normal;
	    transform: rotate(0deg);
	    transition-duration: 0s;
	    transition-property: transform;
	}
	.uni-icon {
	    font-family: uniicons;
	    font-weight: normal;
	}
	
</style>