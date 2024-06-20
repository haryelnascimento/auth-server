package com.example.demo.factory;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import com.example.demo.security.oauth2.user.GovBrOAuth2UserInfo;
import com.example.demo.security.oauth2.user.OAuth2UserInfo;
import com.example.demo.security.oauth2.user.OAuth2UserInfoFactory;
import com.example.demo.vo.UserDTO;

@Provider("govbr")
public class GovBrUserFactory implements UserFactory {

	// @Autowired
	// private CpaUsuarioPortalRepository cpaUsuarioPortalRepository;

	// @Autowired
	// private UserSetorSistRepository userSetorSistRepository;

	// @Autowired
	// private UserGrupoRepository userGrupoRepository;

	// @Autowired
	// private EpadValorParametroService epadValorParametroService;

	// @Autowired
	// private UserRepository userRepository;

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	// @Autowired
	// private UserSistemaRepository userSistemaRepository;

	// @Autowired
	// private OAuth2AuthorizedClientService authorizedClientService;

	// @Autowired
	// private AppProperties appProperties;

	public static final String PESSOA_FISICA = "F";
	public static final String PESSOA_JURIDICA = "J";

	@Override
	public UserDTO createUser(Authentication authentication) {
		DefaultOidcUser userOidc = (DefaultOidcUser) authentication.getPrincipal();

		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
				.getOAuth2UserInfo(((OAuth2AuthenticationToken) authentication)
						.getAuthorizedClientRegistrationId(), userOidc.getAttributes());

		GovBrOAuth2UserInfo govUserInfo = ((GovBrOAuth2UserInfo) oAuth2UserInfo);

		System.out.println("GovBrOAuth2UserInfo: " + govUserInfo);

		UserDTO userDTO = new UserDTO();
		// userDTO = atualizaOuCadastraUsuario(govUserInfo, userDTO);

		// String accessToken = recuperaAccessToken(authentication);
		// List<Empresa> empresas = recuperaEmpresasPessoa(userDTO, accessToken);

		// if(!empresas.isEmpty()) {
		// atualizaOuCadastraEmpresa(empresas, userDTO.getCdUsuario());
		// }

		return userDTO;
	}

	// private void atualizaOuCadastraEmpresa(List<Empresa> empresas, String
	// cdUsuario) {
	// try {
	// for (Empresa empresa : empresas) {
	// User empresaGovBr = cadastraUsuario(empresa.getNuCnpj(), null,
	// empresa.getDeRazaoSocial(), PESSOA_JURIDICA);
	// cadastraUsuarioPortal(empresaGovBr.getCdUsuario(), cdUsuario,
	// empresaGovBr.isCadastroGov());
	// cadastraUsuarioSistema(empresaGovBr.getCdUsuario());
	// cadastraUsuarioGrupo(empresaGovBr.getCdUsuario());
	// cadastraUsuarioNoSetorPortal(empresaGovBr.getCdUsuario());
	// }
	// } catch (InvalidDataAccessApiUsageException e) {
	// throw new InvalidDataAccessApiUsageException(e.getMessage(),e);
	// }
	// }

	// private UserDTO atualizaOuCadastraUsuario(GovBrOAuth2UserInfo govUserInfo,
	// UserDTO userDTO) {
	// try {
	// userDTO.setNuCpfcnpj(govUserInfo.getId());
	// User userGovBr = cadastraUsuario(govUserInfo.getId(), govUserInfo.getEmail(),
	// govUserInfo.getName(), PESSOA_FISICA);
	// userDTO = preencheDTO(userGovBr, userDTO, govUserInfo);

	// cadastraUsuarioPortal(userDTO.getCdUsuario(), null,
	// userGovBr.isCadastroGov());

	// userDTO.setFlSuperUsuario(cadastraUsuarioSistema(userDTO.getCdUsuario()));

	// cadastraUsuarioGrupo(userDTO.getCdUsuario());
	// cadastraUsuarioNoSetorPortal(userDTO.getCdUsuario());

	// } catch (InvalidDataAccessApiUsageException e) {
	// throw new InvalidDataAccessApiUsageException(e.getMessage(),e);
	// }
	// return userDTO;
	// }

	// private User cadastraUsuario(String cpfCnpj,String email, String nmNome,
	// String tpPessoa) {
	// User userGovBr = new User();
	// Optional<User> userOptional = userRepository.findByCdUsuario(cpfCnpj);
	// if(userOptional.isPresent()) {
	// userGovBr = userOptional.get();
	// if(userOptional.get().getDtAtivacao() == null){
	// userGovBr.setDtAtivacao(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
	// userGovBr.setCadastroGov(false);
	// }
	// } else {
	// userGovBr.setNuCpfCnpj(Long.valueOf(cpfCnpj));
	// userGovBr.setNmSenha(criptografaSenha(UUID.randomUUID().toString()));
	// userGovBr.setCdUsuario(cpfCnpj);
	// userGovBr.setTpPessoa(tpPessoa);
	// userGovBr.setTpUsuario("C");
	// userGovBr.setDtAtivacao(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
	// userGovBr.setCadastroGov(true);
	// }
	// userGovBr.setDeEmail(email);
	// userGovBr.setNmUsuario(nmNome);
	// userGovBr.setFlHabilitado("S");
	// userGovBr.setDtUltLoginOk(Timestamp.valueOf(LocalDate.now().atStartOfDay()));

	// userRepository.save(userGovBr);

	// return userGovBr;
	// }

	// private UserDTO preencheDTO(User userGovBr, UserDTO userDTO,
	// GovBrOAuth2UserInfo govUserInfo) {
	// userDTO.setCdUsuario(userGovBr.getCdUsuario());
	// userDTO.setNmUsuario(userGovBr.getNmUsuario());
	// userDTO.setDeEmail(userGovBr.getDeEmail());
	// userDTO.setNuTelefone(govUserInfo.getPhone());

	// return userDTO;
	// }

	// private String criptografaSenha(String deSenha) {
	// return passwordEncoder.encode(deSenha);
	// }

	// private String recuperaAccessToken(Authentication authentication) {

	// OAuth2AuthorizedClient authorizedClient =
	// this.authorizedClientService.loadAuthorizedClient(
	// ((OAuth2AuthenticationToken)
	// authentication).getAuthorizedClientRegistrationId(),
	// authentication.getName());

	// return authorizedClient.getAccessToken().getTokenValue();
	// }

	// private String cadastraUsuarioSistema(String cdUsuario) {

	// // sistema PTL
	// UserSistemaPK idPtl = new UserSistemaPK(cdUsuario,
	// SystemConstants.CDSISTEMA_CPAPTLATEND);
	// Optional<UserSistema> optUserSistemaPtl =
	// userSistemaRepository.findById(idPtl);
	// String flSuperUsuario = "0";

	// if (!optUserSistemaPtl.isPresent()) {
	// UserSistema userSistema = new UserSistema();
	// userSistema.setUserSistemaPK(idPtl);
	// userSistema.setFlCadCompleto(FlSimNao.NAO.getValue());
	// userSistema.setFlSuperUsuario(0);
	// userSistemaRepository.save(userSistema);
	// }else {
	// flSuperUsuario = String.valueOf(optUserSistemaPtl.get().getFlSuperUsuario());
	// }

	// // sistema CPAV
	// UserSistemaPK idCpav = new UserSistemaPK(cdUsuario,
	// SystemConstants.CDSISTEMA_CPAV);
	// Optional<UserSistema> optUserSistemaCpav =
	// userSistemaRepository.findById(idCpav);

	// if (!optUserSistemaCpav.isPresent()) {
	// UserSistema userSistema = new UserSistema();
	// userSistema.setUserSistemaPK(idCpav);
	// userSistema.setFlCadCompleto(FlSimNao.NAO.getValue());
	// userSistema.setFlSuperUsuario(null);
	// userSistemaRepository.save(userSistema);
	// }
	// return flSuperUsuario;
	// }

	// private List<Empresa> recuperaEmpresasPessoa(UserDTO user, String
	// accessToken) {
	// String retorno = "";

	// Empresa[] empresas = null;

	// URL url;
	// try {
	// url = new URL(appProperties.getAuth().getUrlApiGovbr()
	// +"/empresas/v2/empresas?filtrar-por-participante=" + user.getCdUsuario());

	// HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	// conn.setRequestMethod("GET");
	// conn.setRequestProperty("Accept", "application/json");
	// conn.setRequestProperty("authorization", "Bearer "+ accessToken);

	// if (conn.getResponseCode() != 200) {
	// throw new RuntimeException("Falhou : HTTP error code : " +
	// conn.getResponseCode());
	// }

	// String output;
	// BufferedReader br = new BufferedReader(new
	// InputStreamReader((conn.getInputStream())));

	// while ((output = br.readLine()) != null) {
	// retorno += output;
	// }

	// conn.disconnect();

	// ObjectMapper mapper = new ObjectMapper();
	// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

	// empresas = mapper.readValue(retorno,Empresa[].class);

	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// } catch (ProtocolException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }

	// if(empresas == null || empresas.length == 0) {
	// return new ArrayList<Empresa>();
	// }

	// return Arrays.asList(empresas);
	// }

	// private List<Empresa> salvarNovasEmpresas(GovBrOAuth2UserInfo govUserInfo) {
	// List<Empresa> empresas = recuperaEmpresasPessoa(govUserInfo);
	//
	// recuperaIdEmpresas(empresas);
	//
	// return empresaRepository.saveAll(empresas);
	// }

	// private void cadastraUsuarioPortal(String cdUsuario, String
	// cdUsuarioAtreladoGovBr, boolean isCadastroGov) {
	// CpaUsuarioPortal cpaUsuarioPortal = new CpaUsuarioPortal();
	// Optional<CpaUsuarioPortal> userOptional = cpaUsuarioPortalRepository
	// .findByCdUsuario(cdUsuario);

	// if(userOptional.isPresent()) {
	// cpaUsuarioPortal = userOptional.get();
	// cpaUsuarioPortal.setFlPrimeiroLogin("N");
	// if(StringUtil.isNullOrBlank(cpaUsuarioPortal.getFlCriadoLoginGovBr())) {
	// cpaUsuarioPortal.setFlCriadoLoginGovBr("N");
	// }
	// }else {
	// cpaUsuarioPortal.setFlPrimeiroLogin("S");
	// cpaUsuarioPortal.setCdPerfilPortal(1);
	// cpaUsuarioPortal.setCdTermoPortal(epadValorParametroService.buscaPorNome(SystemConstants.PRM_BD_CODIGO_TERMO_PORTAL));
	// if(isCadastroGov) {
	// cpaUsuarioPortal.setFlCadCompletoGovBr("N");
	// }
	// cpaUsuarioPortal.setFlCriadoLoginGovBr("S");
	// cpaUsuarioPortal.setDtCadastro(new Timestamp(new Date().getTime()));
	// }
	// cpaUsuarioPortal.setFlValidado("S");
	// cpaUsuarioPortal.setCdUsuario(cdUsuario);
	// cpaUsuarioPortal.setCdUsuarioAtreladoGovBr(cdUsuarioAtreladoGovBr);

	// cpaUsuarioPortalRepository.save(cpaUsuarioPortal);
	// }

	// private void cadastraUsuarioNoSetorPortal(String cdUsuario) {
	// Integer cdOrgaoSetorPortal =
	// epadValorParametroService.buscaPorNome(SystemConstants.PRM_BD_SETOR_PORTAL);
	// cadastraUserSetorSist(cdOrgaoSetorPortal, cdUsuario,
	// SystemConstants.CDSISTEMA_CPAV);
	// }

	// private void cadastraUserSetorSist(Integer cdOrgaoSetor, String cdUsuario,
	// Integer cdSistema) {

	// UserSetorSistPK pk = new UserSetorSistPK(cdOrgaoSetor, cdUsuario, cdSistema);
	// if (!userSetorSistRepository.existsById(pk)) {
	// UserSetorSist userSetorSist = new UserSetorSist(pk);
	// if
	// ("S".equals(userSetorSistRepository.existsByCdUsuarioAndFlSetorDefault(cdUsuario,
	// "S"))) {
	// userSetorSist.setFlSetorDefault("N");
	// } else {
	// userSetorSist.setFlSetorDefault("S");
	// }
	// userSetorSistRepository.save(userSetorSist);
	// }

	// }

	// private void cadastraUsuarioGrupo(String cdUsuario) {

	// List<String> cdsGrupos =
	// cpaUsuarioPortalRepository.findGruposPerfisPortalByCdUsuario(cdUsuario);
	// if(cdsGrupos != null && !cdsGrupos.isEmpty()){
	// for (String cdGrupo : cdsGrupos) {
	// if(StringUtil.isNotNullOrBlank(cdGrupo)){
	// UserGrupoPK id = new UserGrupoPK(cdUsuario, cdGrupo);
	// Optional<UserGrupo> optUserGrupo = userGrupoRepository.findById(id);
	// if (!optUserGrupo.isPresent()) {
	// UserGrupo userGrupo = new UserGrupo(id);
	// userGrupoRepository.save(userGrupo);
	// }
	// }
	// }
	// }
	// }
}
