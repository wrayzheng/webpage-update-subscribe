<html>
 <head> 
  <meta charset="utf-8" /> 
  <title>test</title> 
 </head> 
 <body> 
  <table class="zhwd-mobile-fullwidth" style="background-color:#f7f9fa" width="100%" cellspacing="0" cellpadding="0" border="0"> 
   <tbody> 
    <tr> 
     <td class="zhwd-mobile-fullwidth" style="background-color: #F7F9FA" width="100%" align="center"> 
      <table class="zhwd-mobile-fullwidth" style="background-color:#f7f9fa" width="552" cellspacing="0" cellpadding="0" border="0"> 
       <tbody> 
        <tr> 
         <td style="font-size:0px; line-height: 0px; color: #f7f9fa" height="0"> 这是一封根据你的兴趣定制的精华合集，希望你每期都能遇见不同的精彩。 </td> 
        </tr> 
        <tr class="zhwd-mobile-hide"> 
         <td> 
          <table width="100%" cellspacing="0" cellpadding="0" border="0"> 
           <tbody> 
            <tr> 
             <td style="font-size:0px; line-height: 0px" height="40"> &nbsp; </td> 
            </tr> 
           </tbody> 
          </table> </td> 
        </tr> 
        <tr> 
         <td> 
          <table class="zhwd-mobile-no-radius" style="width:100%;border-radius:4px;border:1px solid #dedede;margin:0 auto;background-color:#ffffff" cellspacing="0" cellpadding="0"> 
           <tbody> 
            <tr> 
             <td style="padding:45px 35px 45px 35px" class="zhwd-mobile-collapse-padding" align="left"> 
              <table style="width:100%;" cellspacing="0" cellpadding="0" border="0"> 
               <tbody> 
                <tr> 
                 <td> 
                  <table width="100%" cellspacing="0" cellpadding="0" border="0"> 
                   <tbody> 
                    <tr> 
                     <td style="font-size:24px;color:#202121" align="left">网页更新订阅</td> 
                    </tr> 
                   </tbody> 
                  </table> </td> 
                </tr> 
                
                <tr> 
                 <td style="font-size:0px; line-height: 0px" height="20"> &nbsp; </td> 
                </tr>
                 
                <tr> 
                 <td> 
                  <table style="width:100%;"cellspacing="0" cellpadding="0" border="0"> 
                   <tbody> 
                    <tr> 
                     <td style="font-size:0px; line-height: 0px;border-top:1px #f1f4f6 solid" height="20"> &nbsp; </td> 
                    </tr> 
                    <tr> 
                     <td> 
                      <table style="width:100%;" cellspacing="0" cellpadding="0" border="0"> 
                       <tbody>
                       <#list urlMap?keys as key>
                        <tr> 
                         <td> <a href="${urlMap[key].url}" style="font-size:17px;font-weight:bold;text-decoration:none;color:#259;border:none;outline:none" target="_blank">${urlMap[key].title}</a> </td> 
                        </tr> 
                        <tr> 
                         <td style="word-break:break-all;">
                          <ul>
                          	<#list urlMap[key].updateList as update>
                           <li style="list-style-type: square;color: #259;padding: 5px 0;"><a style="text-decoration:none;color:#259;" href="${update.url}">${update.title}</a></li>
                           </#list> 
                          </ul> </td> 
                        </tr>
                        </#list>
                       </tbody> 
                      </table> </td> 
                    </tr> 
                   </tbody> 
                  </table> </td> 
                </tr> 
               </tbody> 
              </table> </td> 
            </tr> 
           </tbody> 
          </table> </td> 
        </tr> 
        <tr> 
         <td> 
          <table width="100%" cellspacing="0" cellpadding="0" border="0"> 
           <tbody> 
            <tr> 
             <td class="zhwd-mobile-small-footer" style="padding:20px;font-size:12px;color:#b6c2cc;line-height:17px" align="center">2017 &copy; <a href="http://app.codebelief.com/webpage-update-subscribe/">网页更新订阅</a></td> 
            </tr> 
           </tbody> 
          </table> </td> 
        </tr> 
       </tbody> 
      </table> </td> 
    </tr> 
   </tbody> 
  </table> 
 </body>
</html>
