package entidad;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;

public class Comprobante {
	
	private Cliente cliente;
	private Cuenta cuenta;
	private Cuota cuota;
	private BigDecimal monto;
	private Date fecha;
	private String CBUDestinatario;
	
	private CuentaNeg ctNeg = new CuentaNegImpl();
	private ClienteNeg clNeg = new ClienteNegImpl();
	
	public Comprobante(Cliente cl, Cuenta ct, BigDecimal mt, String CBU){
		this.cliente = cl;
		this.cuenta = ct;
		this.monto = mt;
		this.fecha = new Date();
		this.CBUDestinatario = CBU;
	}
	
	public Comprobante(Cliente cl, Cuenta ct, BigDecimal mt, Cuota cu) {
		this.cliente = cl;
		this.cuenta = ct;
		this.monto = mt;
		this.cuota = cu;
		this.fecha = new Date();
	}
	
	public String generarComprobanteTransferencia() {
		// Crear una imagen para el comprobante
        BufferedImage bufferedImage = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Cuenta destino = new Cuenta();
        Cliente clDestino = new Cliente();
        try {
			destino = ctNeg.obtenerCuentaPorID(ctNeg.getCuentaFromCBU(CBUDestinatario));
			clDestino = destino.getUsuario();
		} catch (DBException | GenericException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 600, 400);
        g2d.setColor(Color.BLACK);

        // Configurar la fuente
        Font font = new Font("Arial", Font.PLAIN, 20);
        g2d.setFont(font);

        // Escribir información en la imagen
        g2d.drawString("Comprobante de Transferencia", 180, 30);
        g2d.drawString("Fecha: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(fecha), 50, 80);
        // Info emisor
        g2d.drawString("De:", 50, 120);
        g2d.drawString("Cliente: " + cliente.get_Nombre() + " " + cliente.get_Apellido(), 50, 140);
        g2d.drawString("CBU: " + cuenta.getCBU() , 50, 160);
        // Info receptor
        g2d.drawString("Para:", 50, 200);
        g2d.drawString("Destinatario:" + clDestino.get_Nombre() + " " + clDestino.get_Apellido(), 50, 220);
        g2d.drawString("CBU destinatario: " + CBUDestinatario, 50, 240);
        // Saldo
        g2d.drawString("Monto: $" + monto.toString(), 50, 280);

        // Liberar recursos gráficos
        g2d.dispose();

        // Convertir la imagen a una cadena Base64
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Codificar la imagen en Base64
        byte[] imageBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
	}
	
	public String generarComprobanteCuota() {
		BufferedImage bufferedImage = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 600, 400);
        g2d.setColor(Color.BLACK);

        // Configurar la fuente
        Font font = new Font("Arial", Font.PLAIN, 20);
        g2d.setFont(font);

        // Escribir información en la imagen
        g2d.drawString("Comprobante de Pago", 180, 30);
        g2d.drawString("Cuota N°: " + cuota.getNro_Cuota() + " de " + cuota.getCuotas_Totales(), 180, 50);
        g2d.drawString("Fecha: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(fecha), 50, 100);
        // Info
        g2d.drawString("Datos del cliente:", 50, 140);
        g2d.drawString("Cliente: " + cliente.get_Nombre() + " " + cliente.get_Apellido(), 50, 160);
        g2d.drawString("N° de cuenta: " + cuenta.getNumeroCuenta() , 50, 180);
        // Monto
        g2d.drawString("Monto: $" + monto.toString(), 50, 210);

        // Liberar recursos gráficos
        g2d.dispose();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
	}
}
