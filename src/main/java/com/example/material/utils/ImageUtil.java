

package com.example.material.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ImageUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(ImageUtil.class);
	public static final String DEFAULT_IMG_TYPE = "JPEG";

	private ImageUtil() {
	}

	public static byte[] toByteArray(BufferedImage src, String type) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(src, defaultString(type, "JPEG"), os);
		return os.toByteArray();
	}

	public static BufferedImage readImage(String srcImageFile) {
		try {
			return ImageIO.read(new File(srcImageFile));
		} catch (IOException var2) {
			LOGGER.error("Error readImage", var2);
			return null;
		}
	}

	public static BufferedImage readImage(File srcImageFile) {
		try {
			return ImageIO.read(srcImageFile);
		} catch (IOException var2) {
			LOGGER.error("Error readImage", var2);
			return null;
		}
	}

	public static BufferedImage readImage(InputStream srcInputStream) {
		try {
			return ImageIO.read(srcInputStream);
		} catch (IOException var2) {
			LOGGER.error("Error readImage", var2);
			return null;
		}
	}

	public static BufferedImage readImage(URL url) {
		try {
			return ImageIO.read(url);
		} catch (IOException var2) {
			LOGGER.error("Error readImage", var2);
			return null;
		}
	}

	public static final void zoomScale(BufferedImage src, OutputStream output, String type, double scale, boolean flag) {
		try {
			int width = src.getWidth();
			int height = src.getHeight();
			if (flag) {
				width = Long.valueOf(Math.round((double)width * scale)).intValue();
				height = Long.valueOf(Math.round((double)height * scale)).intValue();
			} else {
				width = Long.valueOf(Math.round((double)width / scale)).intValue();
				height = Long.valueOf(Math.round((double)height / scale)).intValue();
			}

			Image image = src.getScaledInstance(width, height, 1);
			BufferedImage tag = new BufferedImage(width, height, 1);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, (ImageObserver)null);
			g.dispose();
			ImageIO.write(tag, defaultString(type, "JPEG"), output);
			output.close();
		} catch (IOException var11) {
			LOGGER.error("Error in zoom image", var11);
		}

	}

	public static final void zoomFixed(BufferedImage src, OutputStream output, String type, int height, int width, boolean bb, Color fillColor) {
		try {
			double ratio = 0.0D;
			src.getScaledInstance(width, height, 4);
			if (src.getHeight() > src.getWidth()) {
				ratio = Integer.valueOf(height).doubleValue() / (double)src.getHeight();
			} else {
				ratio = Integer.valueOf(width).doubleValue() / (double)src.getWidth();
			}

			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), (RenderingHints)null);
			Image itemp = op.filter(src, (BufferedImage)null);
			if (bb) {
				BufferedImage image = new BufferedImage(width, height, 1);
				Graphics2D g = image.createGraphics();
				Color fill = fillColor == null ? Color.white : fillColor;
				g.setColor(fill);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth((ImageObserver)null)) {
					g.drawImage(itemp, 0, (height - itemp.getHeight((ImageObserver)null)) / 2, itemp.getWidth((ImageObserver)null), itemp.getHeight((ImageObserver)null), fill, (ImageObserver)null);
				} else {
					g.drawImage(itemp, (width - itemp.getWidth((ImageObserver)null)) / 2, 0, itemp.getWidth((ImageObserver)null), itemp.getHeight((ImageObserver)null), fill, (ImageObserver)null);
				}

				g.dispose();
				itemp = image;
			}

			ImageIO.write((BufferedImage)itemp, defaultString(type, "JPEG"), output);
			output.close();
		} catch (IOException var14) {
			LOGGER.error("Error in zoom image", var14);
		}

	}

	public static final void crop(BufferedImage src, OutputStream output, String type, int x, int y, int width, int height) {
		try {
			int srcWidth = src.getHeight();
			int srcHeight = src.getWidth();
			if (srcWidth > 0 && srcHeight > 0) {
				Image image = src.getScaledInstance(srcWidth, srcHeight, 1);
				ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
				Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(width, height, 1);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, width, height, (ImageObserver)null);
				g.dispose();
				ImageIO.write(tag, defaultString(type, "JPEG"), output);
				output.close();
			}
		} catch (Exception var14) {
			LOGGER.error("Error in cut image", var14);
		}

	}

	public static final void sliceWithNumber(BufferedImage src, IMultiOutputStream mos, String type, int prows, int pcols) {
		try {
			int rows = prows > 0 && prows <= 20 ? prows : 2;
			int cols = pcols > 0 && pcols <= 20 ? pcols : 2;
			int srcWidth = src.getHeight();
			int srcHeight = src.getWidth();
			if (srcWidth > 0 && srcHeight > 0) {
				Image image = src.getScaledInstance(srcWidth, srcHeight, 1);
				int destWidth = srcWidth % cols == 0 ? srcWidth / cols : srcWidth / cols + 1;
				int destHeight = srcHeight % rows == 0 ? srcHeight / rows : srcHeight / rows + 1;

				for(int i = 0; i < rows; ++i) {
					for(int j = 0; j < cols; ++j) {
						ImageFilter cropFilter = new CropImageFilter(j * destWidth, i * destHeight, destWidth, destHeight);
						Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
						BufferedImage tag = new BufferedImage(destWidth, destHeight, 1);
						Graphics g = tag.getGraphics();
						g.drawImage(img, 0, 0, (ImageObserver)null);
						g.dispose();
						ImageIO.write(tag, defaultString(type, "JPEG"), mos.buildOutputStream(new Integer[]{i, j}));
					}
				}
			}
		} catch (Exception var18) {
			LOGGER.error("Error in slice image", var18);
		}

	}

	public static final void sliceWithSize(BufferedImage src, IMultiOutputStream mos, String type, int pdestWidth, int pdestHeight) {
		try {
			int destWidth = pdestWidth <= 0 ? 200 : pdestWidth;
			int destHeight = pdestHeight <= 0 ? 150 : pdestHeight;
			int srcWidth = src.getHeight();
			int srcHeight = src.getWidth();
			if (srcWidth > destWidth && srcHeight > destHeight) {
				Image image = src.getScaledInstance(srcWidth, srcHeight, 1);
				int cols = srcWidth % destWidth == 0 ? srcWidth / destWidth : srcWidth / destWidth + 1;
				int rows = srcHeight % destHeight == 0 ? srcHeight / destHeight : srcHeight / destHeight + 1;

				for(int i = 0; i < rows; ++i) {
					for(int j = 0; j < cols; ++j) {
						ImageFilter cropFilter = new CropImageFilter(j * destWidth, i * destHeight, destWidth, destHeight);
						Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
						BufferedImage tag = new BufferedImage(destWidth, destHeight, 1);
						Graphics g = tag.getGraphics();
						g.drawImage(img, 0, 0, (ImageObserver)null);
						g.dispose();
						ImageIO.write(tag, defaultString(type, "JPEG"), mos.buildOutputStream(new Integer[]{i, j}));
					}
				}
			}
		} catch (Exception var18) {
			LOGGER.error("Error in slice image", var18);
		}

	}

	public static final void convert(BufferedImage src, OutputStream output, String formatName) {
		try {
			ImageIO.write(src, formatName, output);
			output.close();
		} catch (Exception var4) {
			LOGGER.error("Error in convert image", var4);
		}

	}

	public static final void gray(BufferedImage src, OutputStream output, String type) {
		try {
			ColorSpace cs = ColorSpace.getInstance(1003);
			ColorConvertOp op = new ColorConvertOp(cs, (RenderingHints)null);
			src = op.filter(src, (BufferedImage)null);
			ImageIO.write(src, defaultString(type, "JPEG"), output);
			output.close();
		} catch (IOException var5) {
			LOGGER.error("Error in gray image", var5);
		}

	}

	public static final void textStamp(BufferedImage src, OutputStream output, String type, String text, Font font, Color color, int position, int x, int y, float alpha) {
		try {
			int width = src.getWidth((ImageObserver)null);
			int height = src.getHeight((ImageObserver)null);
			BufferedImage image = new BufferedImage(width, height, 1);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, (ImageObserver)null);
			g.setColor(color);
			g.setFont(font);
			g.setComposite(AlphaComposite.getInstance(10, alpha));
			ImagePosition boxPos = new ImagePosition(width, height, calcTextWidth(text) * font.getSize(), font.getSize(), position);
			g.drawString(text, boxPos.getX(x), boxPos.getY(y));
			g.dispose();
			ImageIO.write(image, defaultString(type, "JPEG"), output);
			output.close();
		} catch (Exception var15) {
			LOGGER.error("Error in textStamp image", var15);
		}

	}

	public static final void imageStamp(BufferedImage src, OutputStream output, String type, BufferedImage stamp, int position, int x, int y, float alpha) {
		try {
			int width = src.getWidth();
			int height = src.getHeight();
			BufferedImage image = new BufferedImage(width, height, 1);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, (ImageObserver)null);
			int stampWidth = stamp.getWidth();
			int stampHeight = stamp.getHeight();
			g.setComposite(AlphaComposite.getInstance(10, alpha));
			ImagePosition boxPos = new ImagePosition(width, height, stampWidth, stampHeight, position);
			g.drawImage(stamp, boxPos.getX(x), boxPos.getY(y), stampWidth, stampHeight, (ImageObserver)null);
			g.dispose();
			ImageIO.write(image, defaultString(type, "JPEG"), output);
			output.close();
		} catch (Exception var15) {
			LOGGER.error("Error imageStamp", var15);
		}

	}

	public static final int calcTextWidth(String text) {
		int length = 0;

		for(int i = 0; i < text.length(); ++i) {
			if ((new String(text.charAt(i) + "")).getBytes().length > 1) {
				length += 2;
			} else {
				++length;
			}
		}

		return length / 2;
	}

	public static String defaultString(String str, String defaultStr) {
		return str == null ? defaultStr : str;
	}
}
