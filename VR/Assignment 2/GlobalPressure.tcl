package require vtk
package require vtkinteraction

#
# This example reads a volume dataset, extracts an isosurface that
# represents the skin and displays it.
#

# Create the renderer, the render window, and the interactor. The renderer
# draws into the render window, the interactor enables mouse- and
# keyboard-based interaction with the scene.
#
vtkRenderer aRenderer
vtkRenderWindow renWin
  renWin AddRenderer aRenderer
vtkRenderWindowInteractor iren
  iren SetRenderWindow renWin

# The following reader is used to read a series of 2D slices (images)
# that compose the volume. The slice dimensions are set, and the
# pixel spacing. The data Endianness must also be specified. The reader
# usese the FilePrefix in combination with the slice number to construct
# filenames using the format FilePrefix.%d. (In this case the FilePrefix
# is the root name of the file: quarter.)
vtkStructuredPointsReader v16
  v16 SetFileName "pressure.dat"

# An isosurface, or contour value of 500 is known to correspond to the
# skin of the patient. Once generated, a vtkPolyDataNormals filter is
# is used to create normals for smooth surface shading during rendering.
# The triangle stripper is used to create triangle strips from the
# isosurface these render much faster on may systems.
vtkContourFilter skinExtractor
  skinExtractor SetInputConnection [v16 GetOutputPort]
  eval skinExtractor GenerateValues 100 [[v16 GetOutput] GetScalarRange]
vtkPolyDataMapper skinMapper
  skinMapper SetInputConnection [skinExtractor GetOutputPort]
  eval skinMapper SetScalarRange [[v16 GetOutput] GetScalarRange]
  #skinMapper ScalarVisibilityOff
vtkActor skin
  skin SetMapper skinMapper

# An outline provides context around the data.
#
vtkOutlineFilter outlineData
  outlineData SetInputConnection [v16 GetOutputPort]
vtkPolyDataMapper mapOutline
  mapOutline SetInputConnection [outlineData GetOutputPort]
vtkActor outline
  outline SetMapper mapOutline
  [outline GetProperty] SetColor 1 1 1

vtkLookupTable hueLut
  hueLut SetHueRange  0 .667
  hueLut SetSaturationRange 1 1
  hueLut SetValueRange 1 1
  hueLut Build
  
vtkScalarBarActor scalarBar
	scalarBar SetLookupTable hueLut
	scalarBar SetTitle "Temperature"
	[scalarBar GetPositionCoordinate] SetCoordinateSystemToNormalizedViewport
	[scalarBar GetPositionCoordinate] SetValue 0.01 0.1
	scalarBar SetWidth 0.15
	scalarBar SetHeight 0.9
	
vtkTextActor textActor
	textActor SetInput "Global Pressure"
# It is convenient to create an initial view of the data. The FocalPoint
# and Position form a vector direction. Later on (ResetCamera() method)
# this vector is used to position the camera to look at the data in
# this direction.
vtkCamera aCamera
  aCamera SetViewUp  0 0 -1
  aCamera SetPosition  0 1 0
  aCamera SetFocalPoint  0 0 0
  aCamera ComputeViewPlaneNormal

# Actors are added to the renderer. An initial camera view is created.
# The Dolly() method moves the camera towards the FocalPoint,
# thereby enlarging the image.
aRenderer AddActor outline
aRenderer AddActor skin
aRenderer AddActor scalarBar
aRenderer AddActor textActor
aRenderer SetActiveCamera aCamera
aRenderer ResetCamera
aCamera Dolly 1.5

# Set a background color for the renderer and set the size of the
# render window (expressed in pixels).
aRenderer SetBackground 0 0.15 0.22
renWin SetSize 640 480

# Note that when camera movement occurs (as it does in the Dolly()
#method),the clipping planes often need adjusting. Clipping planes
#consist of two planes: near and far along the view direction. The
# near plane clips out objects in front of the plane the far plane
# clips out objects behind the plane. This way only what is drawn
# between the planes is actually rendered.
aRenderer ResetCameraClippingRange

# Set up a callback (using command/observer) to bring up the Tcl
# command GUI when the keypress-u (UserEvent) key is pressed.
iren AddObserver UserEvent {wm deiconify .vtkInteract}

# Interact with data. The Tcl/Tk event loop is started automatically.
iren Initialize
wm withdraw .

