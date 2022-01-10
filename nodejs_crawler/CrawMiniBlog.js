const cheerio = require('cheerio');
const fs = require('fs');
const request_promise = require('request-promise');
const request = require("request");


crawlerData("https://freetuts.net/khoa-hoc");
crawlerData("https://freetuts.net/khoa-hoc/page/2");

function sleep(milliseconds) {
  const date = Date.now();
  let currentDate = null;
  do {
    currentDate = Date.now();
  } while (currentDate - date < milliseconds);
}
function crawlerData(url) {
    request(url, (error, response, html) => { // Doi url thanh trang can lay
        if (!error && response.statusCode == 200) {
            const $ = cheerio.load(html);
            let data = {}
            let link = []
            $('.bp-entry').each((index2, el2)=>{
                let title = $(el2).find('.bp-head h2 a').text()
                let sublink = $(el2).find('.bp-head h2 a').attr('href')
                let content = $(el2).find('.bp-details p').text()
                link.push({
                    title: title,
                    link: sublink,
                    content: content
                })
                crawlerDataPost(sublink)
                // console.log(link)
            })
              // fs.appendFileSync('Database/Data-Course-Page.json', JSON.stringify(link) + "\n");
              
        } else {
            console.log(error);
        }
    });
}

// function crawlerDataDetais(url){
//     request(url, (error, response, html)=>{
//         if (!error && response.statusCode == 200) {
//             const $ = cheerio.load(html);
//             let main_title = $('#main_title').text().trim()
//             let author = $('span.fn').text().trim();
//             let data = {}
//             let sublink = []
//             $('.bordered a').each((index1, el) =>{
//                 //Craw Link lv1
//                 let getlink = $(el).attr('href');
//                 let title = $(el).text();
//                 sublink.push({
//                     title: title,
//                     sublink: getlink
//                 })
                
//                crawlerDataPost(getlink)
//         })
//             data = {
//                     main_title: main_title,
//                     category: category,
//                     author: author,
//                     link: url,
//                     sublink: sublink
//                 }
//                 fs.appendFileSync('Database/Data-Tool-SubCategory-Link-Page.json', JSON.stringify(data) + "\n");
//                 // console.log(data)
//         }else{
//                 console.log(error)
//             }
//     })
// }

function crawlerDataPost(url){
     request(url, (error, response, html)=>{
        if (!error && response.statusCode == 200) {
            const $ = cheerio.load(html);
           data = {}
           let author = $('span.fn').text();
           author = author.trim();
           let title = $('#main_title').text();
           let image = []
            //Craw Link lv2
             $('.base-box.hasgoto ').each((image1, el1)=> {
                let img = $(el1).find('img').attr('src');
                let alt = $(el1).find('img').attr('alt');
                let vid = $(el1).find('iframe').attr('src')
                image.push({
                    alt: alt,
                    imag: img
                })
                // console.log(image)
            })
             data= {
                title: title,
                link: url,
                author: author,
                image: image
             }
             fs.appendFileSync('Database/Img-Course-Page.json', JSON.stringify(data) + "\n");
             console.log(data)
        }else{
                console.log(error)
            }
    })
            

}

// function crawlerDataDetais2(url, title) {
//     	  request(url, (error, response, html) => { // Doi url thanh trang can lay
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
