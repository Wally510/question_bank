<template>
	<view style="height: 100%;">
		<swiper style="height: 100%;" class="swiper" :current="current" @change="swiperChange">
			<swiper-item v-for="(page, index) in timu1" :key="index">
				<scroll-view style="height: 80%;" scroll-y="true">
					<view style="content: ''; overflow: hidden;"></view>
					<view class="gaodu" style="overflow: hidden; text-overflow: ellipsis; min-height: 50px; margin-left: 2.5%; width: 95%; font-size: 18px; margin-top: 5%;">
						<rich-text :nodes="'('+(index+1)+') '+page.problem"></rich-text>
					</view>
					<view style=" border-top: 1px solid #e5e5e5; margin-top: 2%; height: 2%;"></view>
					<view style="display: flex; margin-top: 5px; margin-left: 2.5%; width: 90%;">
						<view class="biaoqian">{{page.type}}</view>
						<view class="biaoqian">{{page.points}}</view>
						<view class="biaoqian">{{page.difficulty}}</view>
					</view>
					<view style="font-size: 18px; margin-top: 20px; margin-bottom: 20px; color: #b10000;">答案</view>
					<view style="font-size: 18px; min-height: 50px; word-break: break-word;">{{page.answer}}</view>
					<view style="font-size: 18px; margin-top: 20px; margin-bottom: 20px; color: #b10000;">解析</view>
					<view style="font-size: 18px; min-height: 20px; word-break: break-word;">{{page.analysis}}</view>
				</scroll-view>
				<view style="z-index: 50; display: flex; justify-content: center;align-items: center; background-color: #ffffff; position: fixed; bottom: 0px;width: 100%; height: 8%; border-top: 1px solid #cbcbcb;">
					<view>{{index+1}}</view>
				</view>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
	//调用katex
	import 'katex/dist/katex.min.css';
	import katex from 'katex';
	
	export default {
		data() {
			return {
				current: 0,
				timu1:[],
				timu2:[],
			};
		},
		onLoad: function (option) {
			console.log(option.chapter_id); 
			// 获取数据库数据
			uni.request({
			    url: 'http://localhost:8080/main.java.com.atheism.obtainment', // 请求的URL  
			    method: 'POST', // 请求方法POST  
			    data: {  
			        id: option.chapter_id, // 请求的数据  
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
			
			console.log(option.id); 
			this.current=option.id;
		},
		mounted() {
		    const middleElem = document.querySelector(`view:nth-child(${this.current})`);
		    middleElem.scrollIntoView({ behavior: "smooth", block: "center" });
			
			const item = document.querySelectorAll('.gaodu');
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
			swiperChange(e) {
				this.current = e.detail.current;
			}
		}
	};
</script>

<style>
	uni-page-body,html,body{  
	     height: 100%;  
	 }
	
	.biaoqian{
		border: 1px solid #9c9c9c;
		color: #9c9c9c;
		font-size: 15px;
		padding: 2px;
		border-radius: 6px;
		margin-right: 3%;
	}
</style>