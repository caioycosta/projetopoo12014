package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.dominio.GrupoGasto;

public class GrupoViewModel {
		GrupoGasto grp;
		
		/**
		 * @param grp
		 * @param mensagem
		 */
		public GrupoViewModel(String mensagem) {
			
			this.mensagem = mensagem;
		}
		
		public GrupoViewModel(){
			
		}
		
		/**
		 * @param grp
		 * @param mensagem
		 */
		public GrupoViewModel(GrupoGasto grp, String mensagem) {
			super();
			this.grp = grp;
			this.mensagem = mensagem;
		}
		/**
		 * @return the grp
		 */
		public GrupoGasto getGrp() {
			return grp;
		}
		/**
		 * @param grp the grp to set
		 */
		public void setGrp(GrupoGasto grp) {
			this.grp = grp;
		}
		/**
		 * @return the mensagem
		 */
		public String getMensagem() {
			return mensagem;
		}
		/**
		 * @param mensagem the mensagem to set
		 */
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		String mensagem;
}
