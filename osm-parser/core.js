  // Principais objetos e funções em javascript

  // Funções para criptografia
	function keyvalue(sChave) {
	  var keyv = new Array;
  	keyv[1] = 0;
  	keyv[2] = 0;
  	for (i=1; i<sChave.length; i++) {
  		curchr = sChave.charCodeAt(i);
  		keyv[1] = keyv[1] + curchr;
  		keyv[2] = sChave.length;
  	}
		return keyv;
	}

  // Gera um string criptografado
	function codifica(sTxt) {
    var kv = '';
		if ((sTxt=='') || (sChavePrivada=='')) {
			return false;
		}
	  else {
  		kv = keyvalue(sChavePrivada);
  		estr = '';
  		enc = '';
    	for (i=0;i<sTxt.length;i++) {
  			e = sTxt.charCodeAt(i);
  			e = e + kv[1];
  			e = e * kv[2];
  			iNumero = parseInt(Math.random()*25+65);
  			rstr = String.fromCharCode(iNumero);
  			estr = estr + rstr + e;
  		}
  		return estr;
  	}
	}

  // Decodifica um string anteriormente criptografado
	function decodifica(sTxt) {
		if ((sTxt=='') || (sChavePrivada=='')) {
			return false;
		}
	  else {
  		kv = keyvalue(sChavePrivada);
  		estr = '';
  		tmp = 0;
  		for (i=0;i<sTxt.length;i++) {
  			if ( (sTxt.charCodeAt(i) > 64) && (sTxt.charCodeAt(i) < 91) ) {
  				if (tmp!='') {
  					tmp = tmp / kv[2];
  					tmp = tmp - kv[1];
  					estr = estr + String.fromCharCode(tmp);
  					tmp = '';
  				}
  			} else {
  				tmp = tmp + sTxt.substr(i,1);
  			}
  		}
  		tmp = tmp / kv[2];
  		tmp = tmp - kv[1];
  		estr = estr + String.fromCharCode(tmp);
  		return estr;
  	}
	}

  // Retorna em formato codificado, com o hash md5
  function codificaURL(sTexto) {
    return codifica(sTexto)+'&hash='+hex_md5(sTexto);
  }
  
  
function marcacaoFundo(theRow, thePointerColor, sCorBorda)
{
    if (thePointerColor == '' || typeof(theRow.style) == 'undefined') {
        return false;
    }
    if (typeof(document.getElementsByTagName) != 'undefined') {
        var theCells = theRow.getElementsByTagName('td');
    }
    else if (typeof(theRow.cells) != 'undefined') {
        var theCells = theRow.cells;
    }
    else {
        return false;
    }

    var rowCellsCnt  = theCells.length;
    for (var c = 0; c < rowCellsCnt; c++) {
        theCells[c].style.backgroundColor = thePointerColor;
        theCells[c].style.borderColor = sCorBorda;
    }

    return true;
} 
  