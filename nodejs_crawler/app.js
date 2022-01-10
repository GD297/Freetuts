const cheerio = require('cheerio');
const fs = require('fs');
const request_promise = require('request-promise');
const request = require("request");


// crawlerData("https://freetuts.net/programming");
// crawlerData("https://freetuts.net/web-frontend");
// crawlerData("https://freetuts.net/web-backend");
// crawlerData("https://freetuts.net/mobile-development");
// crawlerData("https://freetuts.net/database");
crawlerDataPost("https://freetuts.net/");
// for(i = 2; i<=21;i++){
//     console.log("Page: "+ i)
//     crawlerDataPost("https://freetuts.net/tai-ve/page/"+i);
// }

function crawlerDataPost(url) {
    request(url,  (error, response, html) => { // Doi url thanh trang can lay
        if (!error && response.statusCode == 200) {
            const $ = cheerio.load(html);
            let data
                $('.sub-menu.mom_mega_wrap.mom_mega_col_5 li.menu-item.mom_default_menu_item.menu-item-depth-2 a').each((index1, el) =>{
                    // category = $(eleHead[0]).text();
                    // let getLink = $(el).find("a");
                    // $(getLink).each((index2, el2)=>{
                    //     console.log("ASD")
                    //     let sublink = $(el2).attr("href");
                    //     // if(sublink == "/php-function"){
                    //     //     // sublink = "https://freetuts.net/hoc-javascript/javascript-function";
                    //     //     return;
                    //     // }
                    //     // else if(sublink == "/jquery-api"){
                    //     //     sublink = "https://freetuts.net/hoc-jquery/jquery-api";
                    //     //     return;
                    //     // }
                    //     // else if(sublink == "/html-property"){
                    //     //     sublink = "https://freetuts.net/hoc-html-css/html-property";
                    //     //     return;l
                    //     // }
                    //     let title = $(el2).attr('title')
                    //     link.push({
                    //         title: title,
                    //         link: sublink
                    //     })
                    //     // crawlerDataDetais(url)
                    //
                    //     // console.log(sublink)
                    // let linkSubCate = $(el).find('a').attr('href');
                    // console.log(linkSubCate);
                    // crawler(linkSubCate);
                    // console.log(data)


/*                    let title = $("#main_title").text();
                    let author = $('.fn').text().trim();
                    let create_date = $('time.updated').attr('datetime');
                    let content = $('.entry-content p').text().trim();

                    data ={
                        title: title,
                        author: author,
                        create_date: create_date,
                        content: content
                    }
                    console.log(data)*/
                    // let gr = $('.review-cate.two-column');
                    //  $(gr[0]).find('a').each((index1, el) =>{
                    // let link = $(el).attr('href');
                    // // if(link.startsWith("h",0)){
                    //     console.log(link);
                    //     crawler(link)
                    // // }
                    let link = $(el).attr('href')
                    console.log(link)
                    let title = $(el).text();
                    crawler(link, title)

                    // fs.appendFileSync('D:/Database/More/Data-LapTrinh-Categorize.json', JSON.stringify(data) + "\n");


                    })

                    // i=i+1;
                    // crawlerDataDetais(link, category)
                                    // console.log(link)

                // })
                //                     data={
                //         category: category,
                //         link: link
                //     }
                //                     console.log(data)
                //
                //     console.log(i)

        } else {
            console.log(error);
        }
    });
}

function crawler(url, title) {
    request(url, (error, response, html) => {
        if (!error && response.statusCode == 200) {
            const $ = cheerio.load(html);
            // let link = $('.bordered a').attr('href')
            // let title = $('#main_title').text();

            // crawlerDataDetais(link, title)
            // $('.bordered a').each((index1, el) =>{
            //
            //     let link = $(el).attr('href');
            //     if(link.startsWith("h",0)){
            //         console.log(link);
            //         crawlerDataDetails(link);
            //     }
            //
            // })
            let data = []
            let content = $("div.entry-content");
            if (content != null) {
               let contentString = content.html();
                // let title = $('.base-box.page-wrap h1').text();
                data.push({
                    title: title,
                    content: contentString,
                    link: url
                })
                console.log(data)
                // fs.appendFileSync('D:/Database/More/Data-Programing-Blog-fix.json', JSON.stringify(data) + "\n");


            }
            // })
        }
    })
}
function crawlerDataDetails(url){

    request(url, (error, response, html)=>{
        if (!error && response.statusCode == 200) {
            const $ = cheerio.load(html);
            // let data
            // let date = $('time.updated').attr('datetime')
            // // // let main_title = $('#main_title').text().trim()
            // let author = $('.author.vcard').text();
            // author = author.slice(26,);
            // author = author.trim();
            // let full_content = $('.ads_displayed')
            // let content = $(full_content[0]).text();
            // let title = $('#main_title').text();
            // let sub_cate = "C / C++"
            // // let data = {}
            // // let sublink = []
            // $('.bp-entry ').each((index1, el) =>{
            //     //Craw Link lv1
            //     let getlink = $(el).find('.bp-head a').attr ('href');
            //     // let title = $(el).text();
            //     // sublink.push({
            //     //     title: title,
            //     //     sublink: getlink
            //     // })
            //    crawlerData(getlink)C:\Users\Admin\Documents\JavaScript\nodejs_crawler\app.js
        // })
            // data = {
            //         main_title: main_title,
            //         category: category,
            //         author: author,
            //         link: url,
            //         sublink: sublink
            //     }
                // fs.appendFileSync('Database/Data-Tool-SubCategory-Link-Page.json', JSON.stringify(data) + "\n");
                // console.log(data)
            // data={
            //     sub_cate:sub_cate,
            //     linkBlog:url,
            //     title: title,
            //     time:date,
            //     author: author,
            //     content: content
            // }
            // console.log(data)
            // fs.appendFileSync('D:/Database/Fix/Img/Data-C-C++-SubCate-Fix.json', JSON.stringify(data) + "\n");
            let data = []
            let content = $('div.entry-content')
            if(content != null){
                content = content.html();
                let title = $('#main_title').text();
                // let title = $('.base-box.page-wrap h1').text();
                data.push({
                    title: title,
                    content:content,
                    link:url
                })

                fs.appendFileSync('D:/Database/More/Data-Python-Blog.json', JSON.stringify(data) + "\n");
            }
        }else{
                console.log(error)
            }
    })


}

function crawlerData(url){
     request(url, (error, response, html)=>{
        if (!error && response.statusCode == 200) {
            const $ = cheerio.load(html);
           data = {}
           let author = $('.author.vcard').text();
           author = author.slice(26,)
           author = author.trim();
           let title = $('#main_title').text();
           let image = []
           let video = []
           let full_content = $('.ads_displayed')
           let sub_category = $('li.active a ')
           let sub = $(sub_category[0]).text()
           let content = $(full_content[0]).text();
            //Craw Link lv2
             $('.base-box.hasgoto img').each((image1, el1)=> {
                let img = $(el1).attr('src');
                let alt = $(el1).attr('alt');
                image.push({
                    alt: alt,
                    imag: img
                })
                // console.log(image)
            })             
             $('.base-box.hasgoto iframe').each((image1, el1)=> {
                let vid = $(el1).attr('src');
                video.push({
                    linkVid: vid
                })
                // console.log(image)
            })


             data= {
                sub_category: sub,
                title: title,
                link: url,
                author: author,
                content: content,
                image: image,
                video: video
             }
             fs.appendFileSync('D:/Database/Fix/Data-MonHoc-Blog-Page-Fix.json', JSON.stringify(data) + "\n");
             console.log(data)
        }else{
                console.log(error)
            }
    })
            

}

// function crawlerDataDetais2(url) {
//     	  request("https://freetuts.net", (error, response, html) => { // Doi url thanh trang can lay
//         if (!error && response.statusCode == 200) {
//             const $ = cheerio.load(html);
//     /*            $('.main_container ').each((index1, el1) => {
//     			const title = $(el1).find('.bp-head h2 a').attr('href');
//             })*/
//             let data = []
//             let title = $('#main_title').text();
//             let author = $('.author.vcard').text().trim()
//             author = author.slice(26,)
//             data.push({
//                 link: url,
//             	title: title,
//             	author: author,
//             })
//             let img =[]
//             $('.base-box.hasgoto img').each((image1, el)=> {
//                 let image = $(el).attr("src")
//                 img.push({
//                     title : title,
//                     img: image
//                 })
//             })
//             console.log(data);
//             console.log(img);
// /*            fs.appendFileSync('Database/Data-Programing-Page.json', JSON.stringify(data) + "\n");
//             fs.appendFileSync('Database/Img-Programing-Page.json', JSON.stringify(img) + "\n");*/

//         } else {
//             console.log(error);
//         }
//     });
// }
