            </div> 
            </div>
            <div id="right-column">
                <strong class="h">Informação</strong>
                <div class="box">
                ${info}
                </div>
            </div>
        </div>
        <div id="footer"><p><!-- Developed by <a href="http://twitter.com/umutm">Umut Muhaddisoglu</a> 2008. Updated for HTML5/CSS3 by <a href="http://mediagearhead.com">Giles Wells</a> 2010. --></p></div>
    </div>
    <script type="text/javascript">
    
    var elems = document.getElementsByClassName('confirmation');
    var confirmIt = function (e) {
    	
        if (!confirm('Sílvio pergunta: Está certo dissoom?')) { 
        
        e.preventDefault();
        return false;
        
        }
        
        return true;
    };
    for (var i = 0, l = elems.length; i < l; i++) {
        elems[i].addEventListener('click', confirmIt, false);
    }
    
    </script>
</body>
</html> 