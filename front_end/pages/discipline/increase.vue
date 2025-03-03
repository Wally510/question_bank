<template>
	<view style="height: 100%;">
		<scroll-view style="z-index: 50; position: fixed; width: 100%; white-space: nowrap; background-color: #ffffff;" class="scroll1" scroll-x="true">
			<view style="display: inline-flex; width: 50%; flex-direction: column; text-align: center; line-height: 40px;" :data-current="index" @click="swichNav(index)" v-for="(item,index) in scoll":key='index'>
				<text :class="currentTab==index ? 'select' : ''" >{{item.txt}}</text>
				<view :class="currentTab==index ? 'select-xian' : ''" ></view>
			</view>
		</scroll-view>
		<view style="display: flex; height: 3px; width: 100%;">
			<!-- <view style="width: 50%; background-color: #00aaff; border-radius: 5px;"></view> -->
		</view>
		<swiper :current="currentTab" @change="bindChange" :style="'height:'+viewHeight+'px;'" style="padding-top: 10%;">
			<swiper-item v-for="index in valueIndex" :key="index">
				<scroll-view style="position: absolute; width: 100%; height: 100%;" scroll-y="true" :class="{'tianjia':index==1}">
					<view style="overflow: hidden;"></view>
					<view style="margin-top: 5%; height: 5%; width: 20%; border: 1px solid black;" @click="chooseFile">选择附件</view>
				</scroll-view>
				<scroll-view style="position: absolute; width: 100%; height: 100%;" scroll-y="true" :class="{'tianjia':index==2}">
					<view style="content: ''; overflow: hidden;"></view>
					<form>
						<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
							<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight:bold;">学科</text>
							<view style="position: relative; left: 5%; top: 10px; border-radius: 5px; background-color: #f9f9f9; color: #343434; border: 1px solid #d4d4d4; width: 90%; height: 40px; font-size: 18px;">
								<view v-model="subject_name" style="margin-left: 3%; width: 97%; height: 40px; line-height: 40px; font-size: 18px;">{{subject_name}}</view>
							</view>
						</view>
						<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
							<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight: bold;">章节</text>
							<view style="position: relative; left: 5%; top: 10px; display: flex; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; height: 40px; font-size: 18px;">
								<input v-model="chapter_name" style="margin-left: 3%; width: 95%; height: 40px; font-size: 18px;" type="text":disabled="true" placeholder="请选择章节"/>
								<view class="uni-panel-icon uni-icon" style="width: 5%; text-align: center; line-height: 40px;" @click="display1">&#xe581;</view>
							</view>
							<scroll-view style="position: absolute;left: 5%; margin-top: 3%; border: 1px solid #888888; border-radius: 0 0 10px 10px; width: 90%; height: 120px; background-color: #fff; z-index: 100;" scroll-y="true" :style="{'display':xiala1}">
								<view v-for="item in zhangjiesousuo" :key="item.id" style="margin-left: 5%; margin-right: 5%; height: 40px; line-height: 40px; font-size: 20px;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" @click="zhanghide(item.id,item.name)">
									{{item.name}}
								</view>
							</scroll-view>
						</view>
						<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
							<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight:bold;">题型</text>
							<view style="position: relative; left: 5%; top: 10px; display: flex; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; height: 40px; font-size: 18px;">
								<input v-model="type" style="margin-left: 3%; width: 95%; height: 40px; font-size: 18px;" type="text":disabled="true" placeholder="请选择题型"/>
								<view class="uni-panel-icon uni-icon" style="width: 5%; text-align: center; line-height: 40px;" @click="display2">&#xe581;</view>
							</view>
							<scroll-view style="position: absolute;left: 5%; margin-top: 3%; border: 1px solid #888888; border-radius: 0 0 10px 10px; width: 90%; height: 120px; background-color: #fff; z-index: 100;" scroll-y="true" :style="{'display':xiala2}">
								<view v-for="item in tixingsousuo" :key="item.id" style="margin-left: 5%; margin-right: 5%; height: 40px; line-height: 40px; font-size: 20px;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" @click="tihide(item.name)">
									{{item.name}}
								</view>
							</scroll-view>
						</view>
						<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
							<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight:bold;">知识点</text>
							<view style="position: relative; left: 5%; top: 10px; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; height: 40px; font-size: 18px;">
								<input  v-model="points" style="margin-left: 3%; width: 97%; height: 40px; font-size: 18px;" placeholder="请输入知识点"/>
							</view>
						</view>
						<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
							<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight:bold;">难度</text>
							<view style="position: relative; left: 5%; top: 10px; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; height: 40px; font-size: 18px;">
								<input v-model="difficulty" style="margin-left: 3%; width: 97%; height: 40px; font-size: 18px;" placeholder="请输入难度"/>
							</view>
						</view>
						<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
							<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight:bold;">题目</text>
							<textarea v-model="problem" style="position: relative; left: 5%; top: 10px; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; font-size: 18px;"></textarea>
						</view>
						<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
							<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight:bold;">答案</text>
							<textarea v-model="answer" style="position: relative; left: 5%; top: 10px; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; font-size: 18px;"></textarea>
						</view>
						<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
							<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight:bold;">解析</text>
							<textarea v-model="analysis" style="position: relative; left: 5%; top: 10px; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; font-size: 18px;"></textarea>
						</view>
						<view style="height: 10%;"></view>
					</form>
				</scroll-view>
			</swiper-item>
		</swiper>
		<view style="z-index: 100; display: flex; background-color: #ffffff; position: fixed; bottom: 0px;width: 100%; height: 9%; border-top: 1px solid #7d7d7d;">
			<button style="background-color: #ffffff; border: 1px solid #00aaff; color: #00aaff;" class="zujuan_button" @click="deletejuan">取消</button>
			<button style="background-color: #00aaff; " class="zujuan_button" @click="gozujuan">添加</button>
		</view>
	</view>
</template>

<script>
	import XLSX from '@/common/js/xlsx.mini.min.js'
	function process(data){
		let workbook = XLSX.read(data, {
			type: "binary"
		});
		const sheetName = workbook.SheetNames[0]
		let sheet = workbook.Sheets[sheetName]
		const options = {
			raw: false // 如果不加raw:true的话日期会被读成数字，根据开发需求决定
		}
		const rawData = XLSX.utils.sheet_to_json(sheet, options);
		// do something
	}
	
	//获取刘海以及状态栏区域的空余显示区域
	const getDeviceHeight = () => {
		const res = uni.getSystemInfoSync();
		const system = res.platform;
		if( system ==='ios' ){
			return 44+res.statusBarHeight;
		}else if( system==='android' ){
			return 48+res.statusBarHeight;
		}else{
			return 0;
		}
		
	};
	
	export default {
		data() {
			return {
				subject_id:1,
				subject_name:'高等数学',
				chapter_id:1,
				chapter_name:'',
				type:'',
				points:'',
				difficulty:'',
				problem:'',
				answer:'',
				analysis:'',
				
				xiala1:'none',
				zhangjiesousuo:[],
				xiala2:'none',
				tixingsousuo:[
					{
						id:1,
						name:'选择题',
					},
					{
						id:2,
						name:'多选题',
					},
					{
						id:3,
						name:'填空题',
					},
					{
						id:4,
						name:'判断题',
					},
					{
						id:5,
						name:'计算题',
					}
				],
				currentTab: 0,
				viewHeight:0,
				valueIndex:2,
				scoll:[
					{
						txt:'单题添加'
					},
					{
						txt:'批量添加'
					}
				],
				// 选择文件
				show:false,
				option: {
					url: this.$imgUrl + '/api/Common/upload',
					// 上传附件的key
					name: 'file',
				},
				files: new Map(),
			}
		},
		onLoad(option){
			console.log(option.id);
			this.subject_id = option.id;
			this.subject_name = option.name;
			uni.request({
			    url: 'http://localhost:8080/main.java.com.atheism.chapter', // 请求的URL  
			    method: 'POST', // 请求方法POST  
			    data: {  
			        name: this.subject_name, // 请求的数据  
			    },  
			    header: {  
			        'content-type': 'application/json' // 设置请求头  
			    },  
			    success: (res) => {  
			        // 请求成功处理逻辑  
			        console.log(res.data); // 输出服务器返回的数据  
					
					this.zhangjiesousuo = res.data;
			    },  
			    fail: (err) => {  
			        // 请求失败处理逻辑  
			        console.error('请求失败：', err);  
			    }  
			});
			uni.getSystemInfo({
				success: (res) => {
					this.viewHeight = res.windowHeight - uni.upx2px(80)-getDeviceHeight();
				}
			});
		},
		methods: {
			display1(){
				if(this.xiala1=='block'){
					this.xiala1='none';
				}else{
					this.xiala1='block';
					this.xiala2='none';
				}
			},
			zhanghide(id,name){
				this.chapter_id=id;
				this.chapter_name=name;
				this.xiala1='none';
			},
			display2(){
				if(this.xiala2=='block'){
					this.xiala2='none';
				}else{
					this.xiala2='block';
					this.xiala1='none';
				}
			},
			tihide(item){
				this.type=item;
				this.xiala2='none';
			},
			bindChange: function(e) {
				this.currentTab = e.detail.current;
			},
			swichNav: function(intex) {
				var that = this;
				if (this.currentTab != intex) {
					this.currentTab = intex;
				}
			},
			chooseFile(){
				uni.chooseFile({
					count: 1,
					extension: ['.xls', '.xlsx'],
					success: res => {
						let reader = new FileReader();
						reader.onload = e => {
							const data = e.target.result
							process(data) // data为binary数据
						};
						reader.readAsBinaryString(res.tempFiles[0]);
					}
				});
			},
			gozujuan(){
				uni.request({
				    url: 'http://localhost:8080/main.java.com.atheism.add', // 请求的URL  
				    method: 'POST', // 请求方法POST  
				    data: {  // 请求的数据  
						subject_id: this.subject_id,
						chapter_id: this.chapter_id,
						problem: this.problem,
						answer: this.answer,
						analysis: this.analysis,
						type: this.type,
						points: this.points,
						difficulty: this.difficulty,
				    },  
				    header: {  
				        'content-type': 'application/json' // 设置请求头  
				    },  
				    success: (res) => {  
				        // 请求成功处理逻辑  
				        console.log(res.data); // 输出服务器返回的数据  
						
						uni.navigateTo({
							url:"/pages/discipline/discipline?name="+this.subject_name+"&id="+this.subject_id,
						});
				    },  
				    fail: (err) => {  
				        // 请求失败处理逻辑  
				        console.error('请求失败：', err);  
				    }  
				});
				
				uni.reLaunch({
				    url:"/pages/discipline/increase?id="+this.subject_id
				})
			},
		}
	}
</script>

<style>
	page{
		background-color: #f8f8f8;
	}
	.tianjia{
		display: none;
	}
	.select{
		color: #00aaff;
	}
	.select-xian{
		width: 100%; 
		height: 3px; 
		background-color: #00aaff; 
		border-radius: 5px;
	}
	.biaoqian .biaoqian-zi{
		color: #00aaff;
	}
	.biaoqian .biaoqian-xian{
		width: 100%; 
		height: 3px; 
		background-color: #00aaff; 
		border-radius: 5px;
	}
	.zujuan_button{
		position: relative;
		top: 10px; 
		line-height: 40px; 
		color: #ffffff;  
		/* border-radius: 30px; */
		width: 40%; 
		height: 40px;
	}
	@font-face {
		font-family: uniicons;
		font-weight: normal;
		font-style: normal;
		src: url('~@/static/uni.ttf') format('truetype');
	}
	.uni-panel-icon {
	    
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

