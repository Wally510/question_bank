<template>
	<view>
		<scroll-view style="position: absolute; width: 100%; height: 90%;" scroll-y="true">
			<form>
				<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
					<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight:bold;">试卷名</text>
					<view style="position: relative; left: 5%; top: 10px; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; height: 40px; font-size: 18px;">
						<input v-model="test_paper" style="margin-left: 3%; width: 97%; height: 40px; font-size: 18px;" placeholder="请输入试卷名"/>
					</view>
				</view>
				<view style="position: relative; margin-top: 3%; background-color: #ffffff;  width: 100%; min-height: 100px;">
					<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight: bold;">学科</text>
					<view style="position: relative; left: 5%; top: 10px; display: flex; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; height: 40px;">
						<input style="margin-left: 3%; width: 95%; height: 40px; font-size: 18px;" type="text":disabled="true" v-model="inputxueke" placeholder="请输入学科"/>
						<view class="uni-panel-icon uni-icon" style="width: 5%; text-align: center; line-height: 40px;" @click="display1">&#xe581;</view>
					</view>
					<scroll-view style="position: absolute;left: 5%; margin-top: 3%; border: 1px solid #888888; border-radius: 0 0 10px 10px; width: 90%; height: 120px; background-color: #fff; z-index: 100;" scroll-y="true" :style="{'display':xiala1}">
						<view v-for="item in xuekesousuo" :key="item.id" style="margin-left: 5%; margin-right: 5%; height: 40px; line-height: 40px; font-size: 20px;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" @click="hide(item)">
							{{item.name}}
						</view>
					</scroll-view>
				</view>
				<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
					<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight: bold;">章节</text>
					<view style="position: relative; left: 5%; top: 10px; display: flex; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; height: 40px; font-size: 18px;">
						<scroll-view style="display: flex; white-space:nowrap; margin-left: 3%; margin-right: 2%; width: 90%; height: 40px;" scroll-x="true":style="{'display':shuru1}">
							<view v-for="(zitem,index) in chapter":key="index" style="display: inline-block; min-width: none; height: 25px; margin-top: 6px; border: 1px solid #9c9c9c; color: #505050; border-radius: 6px; margin-right: 1%;">
								<text>{{zitem.name}}</text>
								<image style="margin-left: 3px; height: 80%; top: 10%;" src="../../static/tabs/delate.png" mode="heightFix" @click="zjdelate(index)"></image>
							</view>
						</scroll-view>
						<input style="margin-left: 3%; width: 95%; height: 40px; font-size: 18px;" type="text":disabled="true":style="{'display':shuru2}" placeholder="请输入章节"/>
						<view class="uni-panel-icon uni-icon" style="width: 5%; text-align: center; line-height: 40px;" @click="display2">&#xe581;</view>
					</view>
					<scroll-view style="position: absolute;left: 5%; margin-top: 3%; border: 1px solid #888888; border-radius: 0 0 10px 10px; width: 90%; height: 120px; background-color: #fff; z-index: 100;" scroll-y="true" :style="{'display':xiala2}">
						<view v-for="item in zhangjiesousuo" :key="item.id" style="margin-left: 5%; margin-right: 5%; height: 40px; line-height: 40px; font-size: 20px;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" @click="zhanghide(item)">
							{{item.name}}
						</view>
					</scroll-view>
				</view>
				<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
					<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight: bold;">知识点</text>
					<view style="position: relative; left: 5%; top: 10px; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; height: 40px; font-size: 18px;">
						<input style="margin-left: 3%; width: 97%; height: 40px; font-size: 18px;" placeholder="请输入知识点"/>
					</view>
				</view>
				<view style="margin-top: 3%; padding-bottom: 5%; width: 100%; min-height: 100px; background-color: #ffffff;">
					<view style="width: 100%;">
						<text style="position: relative; left: 5%; top: 10px; font-size: 18px; font-weight: bold; line-height: 30px;">选择题型</text>
					</view>
					<view style="position: relative; top: 10px; width: 100%;">
						<view style="display: flex; width: 100%; text-align: center;">
							<view style="width: 8%;"></view>
							<view style="margin-left: 8%; width: 20%;">题型</view>
							<view style="margin-left: 8%; width: 20%;">数量</view>
							<view style="margin-left: 8%; width: 20%;">分值</view>
						</view>
						<view style="display: flex; width: 100%; height: 50px;" v-for="item2 in txshuliang">
							<view style="display: flex; justify-content: center;align-items: center; margin-top: 10px; height: 30px; width: 16%;">
								<image style="width: 40%;" src="../../static/tabs/add+.png":class="{'fuhao': item2.id!=txshuliang.length-1}" mode="widthFix" @click="add"></image>
								<image style="width: 40%;" src="../../static/tabs/delate.png":class="{'fuhao': item2.id==txshuliang.length-1}" mode="widthFix" @click="delate"></image>
							</view>
							<!-- 题型 -->
							<view style="display: flex; margin: 10px 0 0 0; border-radius: 5px; color: #000000; border: 1px solid #d4d4d4; width: 20%; height: 30px;">
								<input v-model="item2.tixing" style="color: #000000; margin-left: 10%; width: 70%; height: 30px;" type="text":disabled="true"/>
								<view class="uni-panel-icon uni-icon" style="width: 20%; height: 30px; text-align: center; line-height: 30px;" @click="display3(item2)">&#xe581;</view>
							</view>
							<scroll-view style="position: absolute;margin-left: 16%; margin-top: 11%; border: 1px solid #888888; border-radius: 0 0 10px 10px; width: 20%; height: 55px; background-color: #fff; z-index: 100;" scroll-y="true" :style="{'display':item2.xiala}">
								<view v-for="item in tixingsousuo" :key="item.id" style="margin-left: 5%; margin-right: 5%; height: 40px; line-height: 40px; font-size: 20px;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" @click="tihide(item2,item.name)">
									{{item.name}}
								</view>
							</scroll-view>
							
							<!-- 数量 -->
							<view style="display: flex; margin: 10px 0 0 8%; border-radius: 5px; color: #000000; border: 1px solid #d4d4d4; width: 20%; height: 30px;">
								<input type="number" style="color: #000000; margin-left: 10%; width: 70%; height: 30px;" v-model="item2.shuliang" @input="slzongfen($event,item2)"/>
								<view style="color: #000000; width: 20%; height: 30px;">
									<view class="uni-panel-icon uni-icon" style="height: 50%;" @click="slzhengjia(item2)">&#xe580;</view>
									<view class="uni-panel-icon uni-icon" style="height: 50%;" @click="sljianshao(item2)">&#xe581;</view>
								</view>
							</view>
							
							<!-- 分值 -->
							<view style="display: flex; margin: 10px 0 0 8%; border-radius: 5px; color: #000000; border: 1px solid #d4d4d4; width: 20%; height: 30px;">
								<input type="number" style="color: #000000; margin-left: 10%; width: 60%; height: 30px;" v-model="item2.fenzhi" @input="fzzongfen($event,item2)"/>
								<view style="color: #000000; width: 30%; height: 30px; text-align: center; line-height: 30px;">分</view>
							</view>
						</view>
					</view>
					<view style="display: flex; width: 100%; height: 45px;">
						<view style="margin: 20px 0 0 53%; width: 20%; height: 30px; text-align: center; line-height: 30px;">总分</view>
						<view style="display: flex; margin: 20px 0 0 0%; border-radius: 5px; color: #000000; border: 1px solid #d4d4d4; width: 20%; height: 30px;">
							<input style="color: #000000; margin-left: 10%; width: 60%; height: 30px;" :value="this.zfen"/>
							<view style="color: #000000; width: 30%; height: 30px; text-align: center; line-height: 30px;">分</view>
						</view>
					</view>
				</view>
				<view style="margin-top: 3%; width: 100%; min-height: 100px; background-color: #ffffff;">
					<text style="position: relative; left: 5%; top: 10px; font-size: 18px; line-height: 30px; width: 20%; font-weight: bold;">难度</text>
					<view style="position: relative; left: 5%; top: 10px; border-radius: 5px; background-color: #f9f9f9; color: #000000; border: 1px solid #d4d4d4; width: 90%; height: 40px; font-size: 18px;">
						<input v-model="difficulty" style="margin-left: 3%; width: 97%; height: 40px; font-size: 18px;" placeholder="请输入难度"/>
					</view>
				</view>
				<view style="margin-top: 3%; width: 100%; min-height: 1px;">
				</view>
			</form>
		</scroll-view>
		<view style="z-index: 100; display: flex; background-color: #ffffff; position: fixed; bottom: 0px;width: 100%; height: 9%; border-top: 1px solid #7d7d7d;">
			<button style="background-color: #ffffff; border: 1px solid #00aaff; color: #00aaff;" class="zujuan_button" @click="deletejuan">取消</button>
			<button style="background-color: #00aaff; " class="zujuan_button" @click="gozujuan">生成</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				test_paper:'',
				shuliang:0,
				xiala1:'none',
				subject_id:1,
				inputxueke:'',
				xuekesousuo:[],
				xiala2:'none',
				shuru1:'none',
				shuru2:'block',
				chapter:[],
				zhangjiesousuo:[],
				difficulty:0,
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
				zfen:0,
				txshuliang:[
					{
						id:0,
						tixing:'',
						shuliang:0,
						fenzhi:0,
						xiala:'none',
					},
				],
			}
		},
		onLoad() {
			// 获取数据库数据
			uni.request({  
			    url: 'http://localhost:8080/main.java.com.atheism.subject', // 请求的URL  
			    method: 'GET', // 请求方法GET  
			    success: (res) => {  
			        // 请求成功处理逻辑  
			        console.log(res.data); // 输出服务器返回的数据  
					
					this.xuekesousuo=res.data;
			    },  
			    fail: (err) => {  
			        // 请求失败处理逻辑  
			        console.error('请求失败：', err);  
			    }  
			});
		},
		methods: {
			add(){
				var i = this.txshuliang[this.txshuliang.length-1].id + 1;
				this.txshuliang.push({id:i,tixing:'',shuliang:0,fenzhi:0,xiala:'none',});
			},
			delate(){
				this.txshuliang.pop();
			},
			/*oninput(e){
				this.inputxueke = e.target.value;
				this.xuekesousuo=[];
				for(let i=0;i<this.xueke.length;i++){
					var k=0;
					for(let j=0;j<this.inputxueke.length;j++){
						if(this.xueke[i].name.indexOf(this.inputxueke[j]) == -1){
							k=1;
						}
					}
					if(k==0){
						this.xuekesousuo.push(this.xueke[i]);
					}
				}
			}, */
			display1(){
				if(this.xiala1=='block'){
					this.xiala1='none';
				}else{
					this.xiala1='block';
					this.xiala2='none';
					this.xiala3='none';
				}
			},
			display2(){
				if(this.xiala2=='block'){
					this.xiala2='none';
				}else{
					this.xiala2='block';
					this.xiala1='none';
					this.xiala3='none';
				}
			},
			display3(item){
				if(item.xiala=='block'){
					item.xiala='none';
				}else{
					item.xiala='block';
					this.xiala1='none';
					this.xiala2='none';
				}
			},
			tihide(item,name){
				item.tixing = name;
				item.xiala='none';
			},
			zhanghide(item){
				this.shuru1='block';
				this.shuru2='none';
				this.chapter.push({id:item.id,name:item.name,});
				this.xiala2='none';
			},
			zjdelate(i){
				this.chapter.splice(i,1);
				if(this.chapter.length === 0){
					this.shuru1='none';
					this.shuru2='block';
				}
			},
			hide(item){
				this.subject_id=item.id;
				this.inputxueke=item.name;
				this.chapter=[];
				this.shuru1='none';
				this.shuru2='block';
				this.xiala1='none';
				uni.request({
				    url: 'http://localhost:8080/main.java.com.atheism.chapter', // 请求的URL  
				    method: 'POST', // 请求方法POST  
				    data: {  
				        name: this.inputxueke, // 请求的数据  
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
			},
			deletejuan(){
				console.log("取消");
				uni.navigateTo({
					url:"/pages/paper/paper"
				});
			},
			zongfen(){
				var zf = 0;
				for(let i=0;i<this.txshuliang.length;i++){
					zf = zf + this.txshuliang[i].shuliang*this.txshuliang[i].fenzhi;
				}
				this.zfen = zf;
			},
			slzhengjia(item){
				item.shuliang = parseInt(item.shuliang) + 1;
				this.zongfen();
			},
			sljianshao(item){
				item.shuliang = parseInt(item.shuliang) - 1;
				if(item.shuliang <= 0){
					item.shuliang = 0;
				}
				this.zongfen();
			},
			slzongfen(e,item){
				console.log(e.target.value);
				item.shuliang = e.target.value;
				this.zongfen();
			},
			fzzongfen(e,item){
				console.log(e.target.value);
				item.fenzhi = e.target.value;
				this.zongfen();
			},
			gozujuan(){
				uni.request({
				    url: 'http://localhost:8080/main.java.com.atheism.test_paper', // 请求的URL  
				    method: 'POST', // 请求方法POST  
				    data: {  // 请求的数据  
				        test_paper:this.test_paper,
						subject_id:this.subject_id,
						subject_name:this.inputxueke,
						chapter:this.chapter,
						type:this.txshuliang,
						difficulty:this.difficulty,
						
				    },  
				    header: {  
				        'content-type': 'application/json' // 设置请求头  
				    },  
				    success: (res) => {  
				        // 请求成功处理逻辑  
				        console.log(res.data); // 输出服务器返回的数据  
						uni.navigateTo({
							url:"/pages/paper/paper"
						});
				    },  
				    fail: (err) => {  
				        // 请求失败处理逻辑  
				        console.error('请求失败：', err);  
				    }  
				});
			}
		}
	}
</script>

<style>
	page{
		background-color: #f8f8f8;
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
	.zujuan_button{
		position: relative;
		top: 10px; 
		line-height: 40px; 
		color: #ffffff;  
		/* border-radius: 30px; */
		width: 40%; 
		height: 40px;
	}
	.fuhao{
		display: none;
	}
</style>