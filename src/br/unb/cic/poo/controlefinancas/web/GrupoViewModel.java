package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.dominio.GrupoGasto;

/**
 * @author CaioYuri
 * usado para enviar/receber dados da view de grupos de gasto
 */
public class GrupoViewModel extends ViewModelBase {
		GrupoGasto grp;
		
		
		/**
		 * @param mensagem
		 * cria um novo obj grupo view model
		 */
		public GrupoViewModel(String mensagem) {
			
			this.mensagem = mensagem;
		}
		
		/**
		 * cria um novo obj grupo view model
		 */
		public GrupoViewModel(){
			
		}
		
		/**
		 * @param grp
		 * @param mensagem
		 * cria um novo obj grupo view model
		 */
		public GrupoViewModel(GrupoGasto grp, String mensagem) {
			super();
			this.grp = grp;
			this.mensagem = mensagem;
		}
		/**
		 * @return o grp
		 */
		public GrupoGasto getGrp() {
			return grp;
		}
		/**
		 * @param grp o grp para setar
		 */
		public void setGrp(GrupoGasto grp) {
			this.grp = grp;
		}
		/**
		 * @return o mensagem
		 */
		public String getMensagem() {
			return mensagem;
		}
		/**
		 * @param mensagem o mensagem para setar
		 */
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		String mensagem;
}
