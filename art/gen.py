#!/usr/bin/env python3
from os import listdir, system
from os.path import exists
from gi.repository import Rsvg# cairo

# see https://developer.android.com/guide/practices/screens_support.html
# see https://developer.android.com/design/style/iconography.html

# So, to create an icon for different densities, you should follow the
# 2:3:4:6:8 scaling ratio between the five primary densities (medium, high,
# x-high, xx-high, and xxx-high respectively). For example, consider that the
# size for a launcher icon is specified to be 48x48 dp. This means the baseline
# (MDPI) asset is 48x48 px, and the high-density(HDPI) asset should be 1.5x the
# baseline at 72x72 px, and the x-high density (XHDPI) asset should be 2x the
# baseline at 96x96 px, and so on.
SIZES = (
		#(16, 'xxxh', ),
		#(12, 'xxh', ),
		#(8, 'xh', ),
		#(6, 'h', ),
		(4, 'm', ),
		#(3, 'l', ),
	)

def glob(w):
	for n in listdir('.'):
		if n.endswith(w):
			yield n[:-len(w)]

def conv_svg(width, height, basename):
	for d, suffix in SIZES:
		target = "../src/main/res/drawable-{}dpi/{}.png".format(suffix, basename, )
		if not exists(target):
			# this is to not unintenionally creat new images, if you want so
			# disable this check
			raise Exception("target doesn't exists: {}".format(basename))
		system("inkscape -e {} -C -w {} -h {} {}.svg".format(
			target, int(width * d / 4), int(height * d / 4), basename,
			))

handle = Rsvg.Handle()
for n in glob('.svg'):
	svg = handle.new_from_file(n+'.svg')
	width, height = svg.get_properties('width', 'height')
	# TODO use cairo like in
	#surface = cairo.ImageSurface(cairo.FORMAT_ARGB32, width, height)
	#context = cairo.Context(surface)
	#svg.render_cairo(context)
	conv_svg(width, height, n)

