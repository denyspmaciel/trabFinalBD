package JDBC.Principal;

import java.util.ArrayList;
import java.util.Scanner;

import JDBC.Dao.AtorDAO;
import JDBC.Dao.FilmeDAO;
import JDBC.Dao.Filmes_AssistidosDAO;
import JDBC.Dao.Filmes_PreferidosDAO;
import JDBC.Dao.Atores_PreferidosDAO;
import JDBC.Dao.UsuarioDAO;
import JDBC.Pojo.Ator;
import JDBC.Pojo.Atores_Preferidos;
import JDBC.Pojo.Filme;
import JDBC.Pojo.Filmes_Assistidos;
import JDBC.Pojo.Filmes_Preferidos;
import JDBC.Pojo.Usuario;

public class Principal {

	public static void main(String[] args) {
		UsuarioDAO usuarioDao = new UsuarioDAO();
		FilmeDAO filmeDao = new FilmeDAO();
		AtorDAO atorDao = new AtorDAO();
		Atores_PreferidosDAO atpDao = new Atores_PreferidosDAO();
		Filmes_AssistidosDAO fasDao = new Filmes_AssistidosDAO();
		Filmes_PreferidosDAO fpDao = new Filmes_PreferidosDAO();

		int opcao = 1;
		Scanner sc = new Scanner(System.in);
		System.out.println("Hello Humano! Seja Welcome!\n");

		while(opcao != 0) {
			System.out.println("--- Menu ---");

			System.out.println("--- 1: Listar opções de Usuário ---");
			System.out.println("--- 2: Listar opções de Ator/Atriz ---");
			System.out.println("--- 3: Listar opções de Filme ---");
			System.out.println("--- 0: Sair ---");

			System.out.print("\nSua escolha: ");
			opcao = sc.nextInt();
			sc.nextLine();//Limpando o buffer

			if(opcao == 1) {
				System.out.println();
				System.out.println("--- Menu do Usuário ---");
				System.out.println("--- 1: Cadastrar Usuário ---");
				System.out.println("--- 2: Listar Usuários ---");
				System.out.println("--- 3: Remover Usuário ---");
				System.out.println("--- 4: Atualizar informações de um Usuário ---");
				System.out.print("\nSua escolha: ");
				int opUser = sc.nextInt();
				sc.nextLine();//Limpando o buffer

				if(opUser == 1) {
					System.out.println("--- Cadastrando Usuário ---");
					String nome, cidade, endereco;
					System.out.print("\nNome: ");
					nome = sc.nextLine();
					System.out.print("\nCidade: ");
					cidade = sc.nextLine();
					System.out.print("\nEndereço: ");
					endereco = sc.nextLine();

					Usuario user = new Usuario(nome, cidade, endereco);
					if(usuarioDao.addUsuario(user)) 
						System.out.println("\nUsuário cadastrado com sucesso!\n");
					else
						System.out.println("Ocorreu um erro ao cadastrar o usuário.");

				}

				else if(opUser == 2) {
					System.out.println("--- Lista de usuários ---");
					ArrayList<Usuario> usuarios = usuarioDao.listarUsuarios();
					for(Usuario usuario : usuarios) {
						System.out.println(usuario.toString()+"\n");
					}
					System.out.println();
				}

				else if(opUser == 3) {
					System.out.print("ID do usuário que será excluído: ");
					int uid = sc.nextInt();
					if(usuarioDao.delUsuario(uid))
						System.out.println("Usuário excluído!");
					else
						System.out.println("Ocorreu algum erro ao excluir o usuário");
				}

				else if(opUser == 4) {
					System.out.println("--- Atualizando Usuário ---");
					int uid;
					String nome, cidade, endereco;

					System.out.print("\nDigite o ID do usuario: ");
					uid = sc.nextInt(); 
					sc.nextLine();
					System.out.print("\nNovo nome: ");
					nome = sc.nextLine();
					System.out.print("\nNova cidade: ");
					cidade = sc.nextLine();
					System.out.print("\nNovo endereço: ");
					endereco = sc.nextLine();

					Usuario user = new Usuario(uid, nome, cidade, endereco);
					if(usuarioDao.atualizarUsuario(user)) 
						System.out.println("\nUsuário atualizado com sucesso!\n");
					else
						System.out.println("\nOcorreu um erro ao atualizar o usuário.");
				}

				else {
					System.out.println("--- Opção inválida! ---");
					System.out.println();
				}
			}

			else if(opcao == 2) {
				System.out.println();
				System.out.println("--- Menu do Ator/Atriz ---");
				System.out.println("--- 1: Cadastrar Ator/Atriz ---");
				System.out.println("--- 2: Listar Atores/Atrizes ---");
				System.out.println("--- 3: Remover Ator/Atriz ---");
				System.out.println("--- 4: Atualizar informações de um Ator/Atriz ---");
				System.out.println("--- 5: Adicionar Ator/Atriz Preferido(a) ---");
				System.out.println("--- 6: Listar Atores/Atrizes Preferidos ---");
				System.out.print("\nSua escolha: ");

				int opAtor = sc.nextInt();
				sc.nextLine();//Limpando o buffer

				if(opAtor == 1) {
					System.out.println("--- Cadastrar Ator/Atriz ---");
					String nome, cidade;
					System.out.print("\nNome: ");
					nome = sc.nextLine();
					System.out.print("\nCidade: ");
					cidade = sc.nextLine();
					Ator ator = new Ator(nome, cidade);
					if(atorDao.addAtor(ator))
						System.out.println("Ator/Atriz cadastrado(a) com sucesso!");
					else
						System.out.println("Ocorreu um erro algo cadastrar o ator/atriz!");
					System.out.println();

				}

				else if(opAtor == 2) {
					System.out.println("--- Lista de Atores/Atrizes ---");
					ArrayList<Ator> atores = atorDao.listarAtores();
					for(Ator ator: atores) {
						System.out.println(ator.toString()+"\n");
					}
					System.out.println();
				}

				else if(opAtor == 3) {
					System.out.print("ID do ator/atriz que será excluído(a): ");
					int aid = sc.nextInt();
					if(atorDao.delAtor(aid))
						System.out.println("Ator/Atriz excluído!");
					else
						System.out.println("Ocorreu um erro ao excluir o ator/atriz");

				}

				else if(opAtor == 4) {
					System.out.println("--- Atualizando Ator/Atriz ---");
					int aid;
					String nome, cidade;

					System.out.print("\nDigite o ID do ator/atriz: ");
					aid = sc.nextInt(); 
					sc.nextLine();
					System.out.print("\nNovo nome: ");
					nome = sc.nextLine();
					System.out.print("\nNova cidade: ");
					cidade = sc.nextLine();

					Ator ator = new Ator(aid, nome, cidade);
					if(atorDao.atualizarAtor(ator)) 
						System.out.println("\nUsuário atualizado com sucesso!\n");
					else
						System.out.println("\nOcorreu um erro ao atualizar o usuário.");
				}

				else if(opAtor == 5) {
					System.out.print("ID do usuário: ");
					int uid = sc.nextInt();
					System.out.print("ID do ator/atriz: ");
					int aid = sc.nextInt();

					Usuario usuario = usuarioDao.getUsuarioByUid(uid);
					Ator ator = atorDao.getAtorByAid(aid);
					if(atpDao.addAtorPreferido(usuario, ator))
						System.out.println("Sucesso!");
					else
						System.out.println("Ocorreu um erro.");

				}

				else if(opAtor == 6) {
					System.out.println("--- Listagem de Atores Preferidos ---");
					ArrayList<Atores_Preferidos> atores_pref = atpDao.listarAtoresPreferidos();
					for(Atores_Preferidos atores: atores_pref) {
						System.out.println(atores.toString());
					}
					System.out.println();
				}

				else {
					System.out.println("--- Opção inválida ---");
					System.out.println();
				}
			}

			else if(opcao == 3) {
				System.out.println();
				System.out.println("--- Menu de Filme ---");
				System.out.println("--- 1: Cadastrar Filme ---");
				System.out.println("--- 2: Listar Filmes ---");
				System.out.println("--- 3: Remover Filme ---");
				System.out.println("--- 4: Adicionar Filme Preferido ---");
				System.out.println("--- 5: Adicionar Filme Assistido ---");
				System.out.println("--- 6: Listar Filmes Preferidos ---");
				System.out.println("--- 7: Listar Filmes Assistidos ---");
				System.out.println("--- 8: Recomendar filme por ator/atriz ---");
				System.out.println("--- 9: Recomendar filme por gênero ---");
				System.out.print("\nSua escolha: ");

				int opFilme = sc.nextInt();
				sc.nextLine();//Limpando o buffer

				if(opFilme == 1) {
					System.out.println("--- Cadastrar Filme ---");
					String nome, genero;
					int aid_ator1, aid_ator2;
					System.out.print("\nNome: ");
					nome = sc.nextLine();
					System.out.print("\nGênero: ");
					genero = sc.nextLine();
					System.out.print("\nID do primeiro(a) ator/atriz: ");
					aid_ator1 = sc.nextInt();
					System.out.print("\nID do segundo(a) ator/atriz: ");
					aid_ator2 = sc.nextInt();

					Ator at1 = atorDao.getAtorByAid(aid_ator1);
					Ator at2 = atorDao.getAtorByAid(aid_ator2);

					Filme filme = new Filme(nome, genero, at1, at2);
					if(filmeDao.addFilme(filme))
						System.out.println("Filme cadastrado com sucesso!\n");
					else
						System.out.println("Ocorreu um erro ao cadastrar o filme.\n");
				}

				else if(opFilme == 2) {
					System.out.println("--- Lista de Filmes ---");
					ArrayList<Filme> filmes = filmeDao.listarFilmes();
					for(Filme filme: filmes) {
						System.out.println(filme.toString()+"\n");
					}
					System.out.println();
				}

				else if(opFilme == 3) {
					System.out.print("ID do filme que será removido: ");
					int fid = sc.nextInt();
					if(filmeDao.delFilme(fid))
						System.out.println("Filme removido!\n");
					else
						System.out.println("Ocorreu um erro ao remover o filme.\n");
				}

				else if(opFilme == 4) {
					System.out.println();
					int uid;
					String genero;
					System.out.print("\nID do usuário: ");
					uid = sc.nextInt();
					sc.nextLine();
					System.out.print("\nGênero do filme: ");
					genero = sc.nextLine();

					Usuario user = usuarioDao.getUsuarioByUid(uid);
					Filmes_Preferidos fp = new Filmes_Preferidos(user, genero);

					if(fpDao.addFilmePreferido(fp))
						System.out.println("Sucesso!");
					else
						System.out.println("Erro ao inserir filme preferido.");

				}

				else if(opFilme == 5) {
					System.out.print("ID do usuário: ");
					int uid = sc.nextInt();
					System.out.print("ID do filme: ");
					int fid = sc.nextInt();
					Usuario usuario = usuarioDao.getUsuarioByUid(uid);
					Filme filme = filmeDao.getFilmeByFid(fid);
					if(fasDao.addFilmeAssistido(usuario, filme))
						System.out.println("Sucesso!");
					else
						System.out.println("Ocorreu um erro.");		
				}

				else if(opFilme == 6) {
					System.out.println("--- Listagem de Filmes Preferidos ---");
					ArrayList<Filmes_Preferidos> filmes_pref = fpDao.listarFilmesPreferidos();
					for(Filmes_Preferidos filmes: filmes_pref) {
						System.out.println(filmes.toString());
					}
					System.out.println();
				}

				else if(opFilme == 7) {
					System.out.println("--- Listagem de Filmes Assistidos ---");
					ArrayList<Filmes_Assistidos> filmes_assi = fasDao.listarFilmesAssistidos();
					for(Filmes_Assistidos filmes: filmes_assi) {
						System.out.println(filmes.toString());
					}
					System.out.println();
				}
				
				else if(opFilme == 8) {
					int uid;
					System.out.print("\nID do usuário: ");
					uid = sc.nextInt();
					ArrayList<Filme> filmes = filmeDao.recomendarPorAtor(uid);
					for(Filme f: filmes) {
						System.out.println("Filme: "+f.getNome());
						System.out.println("Gênero: "+f.getGenero());
					}
				}

				else if(opFilme == 9) {
					int uid;
					System.out.print("\nID do usuário");
					uid = sc.nextInt();
					ArrayList<Filme> filmes = filmeDao.recomendarPorGenero(uid);
					for(Filme f: filmes) {
						System.out.println("Filme: "+f.getNome());
						System.out.println("Gênero: "+f.getGenero());
					}

				}
				
				else {
					System.out.println();
					System.out.println("--- Opção inválida! ---");
					System.out.println();
				}

			}

			else if(opcao == 0) {
				System.out.println();
				System.out.println("--- Obrigado por usar nosso sistema! ---");
				sc.close();
				break;
			}

			else {
				System.out.println();
				System.out.println("--- Opção inválida! ---");
				System.out.println();
			}
		}
	}
}

