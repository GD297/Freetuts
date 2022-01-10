const cheerio = require('cheerio');
const fs = require('fs');
const request_promise = require('request-promise');
const request = require("request");


// crawlerData("https://freetuts.net/programming");
// crawlerData("https://freetuts.net/web-frontend");
// crawlerData("https://freetuts.net/web-backend");
// crawlerData("https://freetuts.net/mobile-development");
// crawlerData("https://freetuts.net/database");
crawlerDataPost("https://freetuts.net/khoa-hoc  ");
// for(i = 2; i<=21;i++){
//     crawlerDataDetais("https://freetuts.net/mon-hoc/page/"+i); 
// }

function crawlerDataPost(url) {
    request(url, (error, response, html) => { // Doi url thanh trang can lay
        if (!error && response.statusCode == 200) {
            const $ = cheerio.load(html);
            let course = []
            let data = []
            // $('#course_lists').each((index1, el1) =>{
            //     let head = $(el1).find('.table_coupon th').text().trim()
            //     console.log(head)
                $('.aff-price-wrapper tr').each((index1, el) =>{
                   // let title_course = $(el).find('.main-title').text().trim();
                   // let price = $(el).find('.price-red').text()
                   let link = $(el).find('a').attr('href');
                   // let img = $(el).find('img').attr('src');
                   // // console.log(link)
                   // // console.log(title_course)
                   // // console.log(price)
                   // // console.log(img)
                   //  course.push({
                   //      link: link,
                   //      title_course: title_course,
                   //      price: price,
                   //      img: img
                   //  })
                   
                })
                // data.push({
                //     header: head,
                //     course: course
                // })


            // })
            console.log(data)
                // data={
                //     // category: category,
                //     link: link
                // }

                fs.appendFileSync('D:/Database/Fix/Data-Courses-SubCate-Fix.json', JSON.stringify(data) + "\n");                      
        } else {
            console.log(error);
        }
    });
}

function crawlerDataDetais(url){
    request(url, (error, response,   html)=>{
        if (!error && response.statusCode == 200) {
            const $ = cheerio.load(html);
            // let main_title = $('#main_title').text().trim()
            // let author = $('span.fn').text().trim();
            // let data = {}
            // let sublink = []
            $('.bp-entry ').each((index1, el) =>{
                //Craw Link lv1
                let getlink = $(el).find('.bp-head a').attr ('href');
                // let title = $(el).text();
                // sublink.push({
                //     title: title,
                //     sublink: getlink
                // })
               crawlerData(getlink)
        })
            // data = {
            //         main_title: main_title,
            //         category: category,
            //         author: author,
            //         link: url,
            //         sublink: sublink
            //     }
                // fs.appendFileSync('Database/Data-Tool-SubCategory-Link-Page.json', JSON.stringify(data) + "\n");
                // console.log(data)
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
